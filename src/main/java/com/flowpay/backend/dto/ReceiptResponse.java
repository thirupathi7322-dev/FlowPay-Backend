package com.flowpay.backend.dto;

public class ReceiptResponse {

    private Long id;
    private String fileName;
    private String fileType;
    private String downloadUrl;

    public ReceiptResponse() {
    }

    public ReceiptResponse(
            Long id,
            String fileName,
            String fileType,
            String downloadUrl) {

        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.downloadUrl = downloadUrl;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}