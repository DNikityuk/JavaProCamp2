package org.procamp.exception;

public class DAOAppException extends RuntimeException {
    public DAOAppException(String msg) {
        super(msg);
    }
    public DAOAppException() {
        super();
    }
}
