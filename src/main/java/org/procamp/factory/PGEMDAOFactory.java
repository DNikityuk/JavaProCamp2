package org.procamp.factory;

import org.procamp.dao.IActivityDAO;
import org.procamp.dao.IBuildingDAO;
import org.procamp.dao.IReportDAO;
import org.procamp.dao.IUserDAO;
import org.procamp.dao.pgemhibernate.PGEMActivityDAO;
import org.procamp.dao.pgemhibernate.PGEMBuildingDAO;
import org.procamp.dao.pgemhibernate.PGEMReportDAO;
import org.procamp.dao.pgemhibernate.PGEMUserDAO;
import org.procamp.model.EntityManagerDataSource;
import org.procamp.model.entity.Activity;
import org.procamp.model.entity.Building;
import org.procamp.model.entity.Report;
import org.procamp.model.entity.User;

public class PGEMDAOFactory extends DAOFactory {


    public PGEMDAOFactory() {
        this.dataSource = new EntityManagerDataSource(EntityManagerFactoryUtil.getEntityManager());
    }

    @Override
    public void close() throws Exception {
        super.close();
    }

    @Override
    public IActivityDAO getActivityDAO() {
        return new PGEMActivityDAO((EntityManagerDataSource) dataSource, Activity.class);
    }

    @Override
    public IUserDAO getUserDAO() {
        return new PGEMUserDAO((EntityManagerDataSource) dataSource, User.class);
    }

    @Override
    public IBuildingDAO getBuildingDAO() {
        return new PGEMBuildingDAO((EntityManagerDataSource) dataSource, Building.class);
    }

    @Override
    public IReportDAO getReportDAO() {
        return new PGEMReportDAO((EntityManagerDataSource) dataSource, Report.class);
    }
}
