package com.mn.esimstore.security.exception;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -6671629243336655593L;

    private final int status;
    private final String code;
    private final String message;

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public ErrorType getErrorType() {
        return new ErrorType(status, code, getMessage());
    }

    public ServiceException(int status, String code, String message) {
        super();
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public ServiceException(ErrorType errorType) {
        this(errorType.getStatus(), errorType.getCode(), errorType.getMessage());
    }

    public ServiceException(ErrorType errorType, String message) {
        this(errorType.getStatus(), errorType.getCode(), message);
    }
}
