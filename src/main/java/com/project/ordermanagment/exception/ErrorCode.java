package com.project.ordermanagment.exception;

public enum ErrorCode {

    INVALID_DATA("ER-400", "Invalid Data. Details : {0}"),

    UNKNOWN_ORDER_OR_CART("ER-404", "Unknown Order or Cart Details : {0}"),

    ORDER_ALREADY_EXIST("ER-409", "Order already exist. Details: {0}"),

    BAD_DATA("ER-400", "Bad input data. Details : {0}"),

    SYSTEM_EXCEPTION("ER-500", "Internal Server error. Please contact support if the problem persist. Details {0}"),

    SYSTEM_MAINTENANCE("ER-503", "The system is down for maintenance"),

    CART_ALREADY_EXIST("ER-409", "Cart already exist. Details : {0}"),

    NO_SUCH_ORDER("ER-404", "No such Order. Details : {0}");

    private final String code;

    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getErrorCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    //To-do there should be better way to do this
    public String getMessage(String parameter) {
        if (parameter == null) {
            return message;
        }
        return message.replace("{0}", parameter);
    }
}
