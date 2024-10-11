package com.mn.esimstore.security.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ErrorType {
    public static final ErrorType UNAUTHORIZED = new ErrorType(401, "CE0001", "Unauthorized");
    public static final ErrorType SERVICE_ERROR = new ErrorType(500, "SE0000", "Internal Server Error");

    @JsonIgnore
    private int status;
    private String code;
    private String message;

    public ErrorType(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public ErrorType message(String message) {
        setMessage(message);
        return this;
    }
}
