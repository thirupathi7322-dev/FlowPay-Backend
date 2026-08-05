package com.flowpay.backend.exception;

public class ReceiptAlreadyExistsException extends RuntimeException {

    public ReceiptAlreadyExistsException(String message) {
        super(message);
    }
}