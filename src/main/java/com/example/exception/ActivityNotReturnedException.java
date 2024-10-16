package com.example.exception;

public class ActivityNotReturnedException extends RuntimeException {
    ActivityNotReturnedException(String message) {
        super(message);
    }
}
