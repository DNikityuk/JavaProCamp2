package org.procamp.service;

import org.apache.logging.log4j.Logger;
import org.procamp.exception.SystemException;
import org.procamp.factory.DAOFactory;

import static org.procamp.AppConstants.DAO_INJECT;

public class BaseService {


    public DAOFactory getDAOFactory() {
        return DAOFactory.getDAOFactory(DAO_INJECT);
    }

    public DAOFactory getDAOFactory(DAOFactory externalDAOFactory) {
        if (externalDAOFactory == null) {
            return getDAOFactory();
        }
        return externalDAOFactory;
    }

    public void startTransaction(DAOFactory daoFactory, Logger logger) throws SystemException {
        if (daoFactory != null) {
            try {
                daoFactory.getDataSource().startTransaction();
            } catch (Exception e) {
                logger.error(e);
                throw new SystemException(e.getMessage());
            }
        }
    }

    public void commitTransaction(DAOFactory daoFactory, Logger logger) throws SystemException {
        if (daoFactory != null) {
            try {
                daoFactory.getDataSource().commitTransaction();
            } catch (Exception e) {
                logger.error(e);
                throw new SystemException(e.getMessage());
            }
        }
    }

    public void rollbackTransaction(DAOFactory daoFactory, Logger logger) throws SystemException {
        if (daoFactory != null) {
            try {
                if (daoFactory.getDataSource().isInTransaction()) {
                    daoFactory.getDataSource().rollbackTransaction();
                }
            } catch (Exception e) {
                logger.error(e);
                throw new SystemException(e.getMessage());
            }
        }
    }

    public void finalizeConnection(DAOFactory daoFactory, Logger logger) throws SystemException {
        if (daoFactory != null) {
            try {
                daoFactory.getDataSource().releaseConnection();
            } catch (Exception e) {
                logger.error(e);
                throw new SystemException(e.getMessage());
            }
        }
    }


    public void startTransactionIfNeed(DAOFactory daoFactory, DAOFactory externalDAOFactory, Logger logger) throws SystemException {
        if (externalDAOFactory == null) {
            startTransaction(daoFactory, logger);
        }
    }

    public void commitTransactionIfNeed(DAOFactory daoFactory, DAOFactory externalDAOFactory, Logger logger) throws SystemException {
        if (externalDAOFactory == null) {
            commitTransaction(daoFactory, logger);
        }
    }

    public void rollbackTransactionIfNeed(DAOFactory daoFactory, DAOFactory externalDAOFactory, Logger logger) throws SystemException {
        if (externalDAOFactory == null) {
            rollbackTransaction(daoFactory, logger);
        }
    }

    public void finalizeConnectionIfNeed(DAOFactory daoFactory, DAOFactory externalDAOFactory, Logger logger) throws SystemException {
        if (externalDAOFactory == null) {
            finalizeConnection(daoFactory, logger);
        }
    }


}
