package com.flowpay.backend.controller;

import com.flowpay.backend.dto.ReceiptResponse;
import com.flowpay.backend.entity.Receipt;
import com.flowpay.backend.service.ReceiptService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.File;

import java.io.IOException;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/expenses/{expenseId}")
    public ReceiptResponse uploadReceipt(
            @PathVariable Long expenseId,
            @RequestParam("file") MultipartFile file)
            throws IOException {

        return receiptService.uploadReceipt(expenseId, file);
    }
    @GetMapping("/{receiptId}/download")
    public ResponseEntity<Resource> downloadReceipt(
            @PathVariable Long receiptId) {

        Receipt receipt = receiptService.getReceiptEntity(receiptId);

        File file = new File(receipt.getFilePath());

        Resource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + receipt.getFileName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE,
                        receipt.getFileType())
                .body(resource);
    }
}