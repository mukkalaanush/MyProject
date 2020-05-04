package com.project.ordermanagment.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.ResultSet;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    private final String reponse;

    public String getReponse() {
        return reponse;
    }

    public String getStatus() {
        return status;
    }

    private final String status;

    @JsonCreator
    public Result(String response, String status) {
        this.reponse = response;
        this.status = status;
    }

}
