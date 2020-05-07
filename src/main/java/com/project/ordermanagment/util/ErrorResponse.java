package com.project.ordermanagment.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

    private final String error;

    private final String error_description;

    @JsonCreator
    public ErrorResponse(@JsonProperty("error") String error, @JsonProperty("error_description") String error_description) {
        if (error != null) error = error.toLowerCase();
        this.error = error;
        this.error_description = error_description;
    }

    public String getError() {
        return error;
    }

    public String getError_description() {
        return error_description;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "error='" + error + '\'' +
                ", error_description='" + error_description + '\'' +
                '}';
    }

}
