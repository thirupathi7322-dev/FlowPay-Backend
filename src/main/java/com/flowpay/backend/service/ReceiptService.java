package com.flowpay.backend.service;

import com.flowpay.backend.dto.ReceiptResponse;
import com.flowpay.backend.entity.Expense;
import com.flowpay.backend.entity.Receipt;
import com.flowpay.backend.exception.ReceiptAlreadyExistsException;
import com.flowpay.backend.repository.ExpenseRepository;
import com.flowpay.backend.repository.ReceiptRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ExpenseRepository expenseRepository;

    public ReceiptService(
            ReceiptRepository receiptRepository,
            ExpenseRepository expenseRepository) {

        this.receiptRepository = receiptRepository;
        this.expenseRepository = expenseRepository;
    }

    public ReceiptResponse uploadReceipt(
            Long expenseId,
            MultipartFile file) throws IOException {

        // Check Expense
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() ->
                        new RuntimeException("Expense not found"));

        // Prevent duplicate receipt
        if (receiptRepository.findByExpense(expense).isPresent()) {
            throw new ReceiptAlreadyExistsException(
                    "Receipt already exists for this expense."
            );
        }

        // Validate file type
        String contentType = file.getContentType();

        if (contentType == null ||
                !(contentType.equals("image/jpeg")
                        || contentType.equals("image/png")
                        || contentType.equals("application/pdf"))) {

            throw new RuntimeException(
                    "Only JPG, PNG and PDF files are allowed."
            );
        }

        // Validate file size (20 MB)
        if (file.getSize() >  20 * 1024 * 1024) {
            throw new RuntimeException(
                    "File size must not exceed 20 MB."
            );
        }

        // Create uploads folder
        String uploadDir =
                System.getProperty("user.dir")
                        + File.separator
                        + "uploads";

        File directory = new File(uploadDir);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Generate unique filename
        String uniqueFileName =
                UUID.randomUUID()
                        + "_"
                        + file.getOriginalFilename();

        File destination =
                new File(directory, uniqueFileName);

        // Save file
        file.transferTo(destination);

        // Save metadata
        Receipt receipt = new Receipt();

        receipt.setExpense(expense);
        receipt.setFileName(uniqueFileName);
        receipt.setFilePath(destination.getAbsolutePath());
        receipt.setFileType(contentType);

        Receipt saved =
                receiptRepository.save(receipt);

        return new ReceiptResponse(
                saved.getId(),
                saved.getFileName(),
                saved.getFileType(),
                "/api/receipts/"
                        + saved.getId()
                        + "/download"
        );
    }

    public File getReceipt(Long receiptId) {

        Receipt receipt = receiptRepository.findById(receiptId)
                .orElseThrow(() ->
                        new RuntimeException("Receipt not found"));

        return new File(receipt.getFilePath());
    }

    public Receipt getReceiptEntity(Long receiptId) {

        return receiptRepository.findById(receiptId)
                .orElseThrow(() ->
                        new RuntimeException("Receipt not found"));
    }
}