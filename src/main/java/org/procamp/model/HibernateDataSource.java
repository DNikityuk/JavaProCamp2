package org.procamp.model;

import org.procamp.exception.SystemException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateDataSource extends DataSource {

    private Session session;
    private Transaction transaction;

    public HibernateDataSource(Session session) {
        this.session = session;
        transaction = session.getTransaction();
    }

    @Override
    public void startTransaction() throws SystemException {
        if (inTransaction) {
            throw new SystemException("Cannot start transaction - already in transaction");
        }

        if (transaction == null) {
            if (session == null) {
                throw new SystemException("Cannot start transaction - session isn't available");
            }
            transaction = session.getTransaction();
        }

        transaction.begin();
        inTransaction = true;
    }

    @Override
    public void commitTransaction() throws SystemException {
        if (!inTransaction) {
            throw new SystemException("Cannot commit transaction - not in transaction");
        }

        transaction.commit();
        inTransaction = false;
    }

    @Override
    public void rollbackTransaction() throws SystemException {
        if (!inTransaction) {
            throw new SystemException("Cannot rollback transaction - not in transaction");
        }

        transaction.rollback();
        inTransaction = false;
    }

    @Override
    public void releaseConnection() throws SystemException {
        if (session != null) {
            session.close();
        }
    }

    @Override
    public void flush() throws SystemException {
        session.flush();
    }

    @Override
    public void close() throws Exception {
        releaseConnection();
    }

    public Session getSession() {
        return session;
    }
}
