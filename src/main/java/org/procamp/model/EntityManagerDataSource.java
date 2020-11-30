package org.procamp.model;

import org.procamp.exception.SystemException;

import javax.persistence.EntityManager;

public class EntityManagerDataSource extends DataSource {

    private EntityManager entityManager;

    public EntityManagerDataSource(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void startTransaction() throws SystemException {
        if (inTransaction) {
            throw new SystemException("Cannot start transaction - already in transaction");
        }

        entityManager.getTransaction().begin();
        inTransaction = true;
    }

    @Override
    public void commitTransaction() throws SystemException {
        if (!inTransaction) {
            throw new SystemException("Cannot commit transaction - not in transaction");
        }

        entityManager.getTransaction().commit();
        inTransaction = false;
    }

    @Override
    public void rollbackTransaction() throws SystemException {
        if (!inTransaction) {
            throw new SystemException("Cannot rollback transaction - not in transaction");
        }

        entityManager.getTransaction().rollback();
        inTransaction = false;
    }

    @Override
    public void releaseConnection() {
        entityManager.close();
    }

    @Override
    public void flush() throws SystemException {
        entityManager.flush();
    }

    @Override
    public void close() {
        releaseConnection();
    }
}
