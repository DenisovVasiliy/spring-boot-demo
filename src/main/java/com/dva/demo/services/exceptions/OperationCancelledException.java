package com.dva.demo.services.exceptions;

public class OperationCancelledException extends RuntimeException {
    public OperationCancelledException() {
        super();
    }

    public OperationCancelledException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationCancelledException(String message) {
        super(message);
    }
}
