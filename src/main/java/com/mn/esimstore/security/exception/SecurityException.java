package com.mn.esimstore.security.exception;

public class SecurityException extends ServiceException {
    private static final long serialVersionUID = 6471629243336655593L;

    public SecurityException() {
        super(ErrorType.UNAUTHORIZED);
    }

    public SecurityException(String message) {
        super(ErrorType.UNAUTHORIZED, message);
    }
}
