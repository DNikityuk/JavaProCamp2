package org.procamp.factory;

import org.procamp.dao.IActivityDAO;
import org.procamp.dao.IBuildingDAO;
import org.procamp.dao.IReportDAO;
import org.procamp.dao.IUserDAO;
import org.procamp.dao.pghibernate.PGActivityDAO;
import org.procamp.dao.pghibernate.PGBuildingDAO;
import org.procamp.dao.pghibernate.PGReportDAO;
import org.procamp.dao.pghibernate.PGUserDAO;
import org.procamp.model.HibernateDataSource;
import org.procamp.model.entity.Activity;
import org.procamp.model.entity.Building;
import org.procamp.model.entity.Report;
import org.procamp.model.entity.User;

public class PGDAOFactory extends DAOFactory {


    public PGDAOFactory() {
        this.dataSource = new HibernateDataSource(SessionFactoryUtil.getInstance().openSession());
    }

    @Override
    public IActivityDAO getActivityDAO() {
        return new PGActivityDAO((HibernateDataSource) dataSource, Activity.class);
    }

    @Override
    public IUserDAO getUserDAO() {
        return new PGUserDAO((HibernateDataSource) dataSource, User.class);
    }

    @Override
    public IBuildingDAO getBuildingDAO() {
        return new PGBuildingDAO((HibernateDataSource) dataSource, Building.class);
    }

    @Override
    public IReportDAO getReportDAO() {
        return new PGReportDAO((HibernateDataSource) dataSource, Report.class);
    }
}
