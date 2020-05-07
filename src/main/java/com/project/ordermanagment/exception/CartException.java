package com.project.ordermanagment.exception;

public class CartException extends RuntimeException {

    private final ErrorCode errorCode;

    public CartException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public CartException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
