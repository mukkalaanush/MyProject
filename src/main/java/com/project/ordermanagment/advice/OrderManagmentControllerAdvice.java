package com.project.ordermanagment.advice;


import com.project.ordermanagment.util.ErrorResponse;
import com.project.ordermanagment.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class OrderManagmentControllerAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(OrderManagmentControllerAdvice.class);

    @ExceptionHandler(CartException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse adviceCartException(CartException e) {

        LOG.error("IllegalArgumentException occurred during fulfilment of request. Message:" + e.getMessage());

        return createErrorResponse(ErrorCode.INVALID_DATA.name().toLowerCase(), ErrorCode.INVALID_DATA.getMessage(e.getMessage()));
    }

    @ExceptionHandler(OrderException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse adviceOrderException(OrderException e) {

        LOG.error("IllegalArgumentException occurred during fulfilment of request. Message:" + e.getMessage());

        return createErrorResponse(ErrorCode.INVALID_DATA.name().toLowerCase(), ErrorCode.INVALID_DATA.getMessage(e.getMessage()));
    }

    @ExceptionHandler(OrderProjectException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse adviceOrderProjectException(OrderProjectException e) {

        LOG.error("Exception occurred during fulfilment of request. Message:" + e.getMessage());

        return createErrorResponse(ErrorCode.SYSTEM_EXCEPTION.name().toLowerCase(), ErrorCode.SYSTEM_EXCEPTION.getMessage(e.getMessage()));
    }



    private ErrorResponse createErrorResponse(String errorCode, String message) {
        return new ErrorResponse(errorCode, message);
    }

}
