package org.procamp.model;

import org.procamp.exception.SystemException;

public abstract class DataSource implements AutoCloseable {

    boolean inTransaction;

    public abstract void startTransaction() throws SystemException;

    public abstract void commitTransaction() throws SystemException;

    public abstract void rollbackTransaction() throws SystemException;

    public abstract void releaseConnection() throws SystemException;

    public boolean isInTransaction() throws SystemException {
        return inTransaction;
    }

    public abstract void flush() throws SystemException;

}
