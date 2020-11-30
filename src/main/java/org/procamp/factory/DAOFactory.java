package org.procamp.factory;

import org.procamp.dao.IActivityDAO;
import org.procamp.dao.IBuildingDAO;
import org.procamp.dao.IReportDAO;
import org.procamp.dao.IUserDAO;
import org.procamp.model.DataSource;

public abstract class DAOFactory implements AutoCloseable {

    public static final int MANUAL_POSTGRES = 1;
    public static final int ENTITY_MANAGER_POSTGRES = 2;

    protected DataSource dataSource;

    public static DAOFactory getDAOFactory(int whichFactory) {

        switch (whichFactory) {
            case ENTITY_MANAGER_POSTGRES:
                return new PGEMDAOFactory();
            case MANUAL_POSTGRES:
            default:
                return new PGDAOFactory();
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public void close() throws Exception {
        dataSource.close();
    }

    public abstract IActivityDAO getActivityDAO();
    public abstract IUserDAO getUserDAO();
    public abstract IBuildingDAO getBuildingDAO();
    public abstract IReportDAO getReportDAO();

}
