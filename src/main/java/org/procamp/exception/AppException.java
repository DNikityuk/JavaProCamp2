package org.procamp.exception;

public class AppException extends Exception {
    public AppException(String msg) {
        super(msg);
    }

    public AppException(Throwable t) {
        super(t);
    }

    public AppException() {
        super();
    }
}
