package com.project.ordermanagment.exception;

public class OrderProjectException extends RuntimeException {

    private final ErrorCode errorCode;

    public OrderProjectException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public OrderProjectException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
