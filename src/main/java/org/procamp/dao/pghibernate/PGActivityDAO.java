package org.procamp.dao.pghibernate;

import org.procamp.exception.DAOAppException;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.procamp.dao.IActivityDAO;
import org.procamp.model.HibernateDataSource;
import org.procamp.model.entity.Activity;
import org.procamp.model.entity.Building;
import org.procamp.model.entity.Report;
import org.procamp.model.entity.User;

import java.util.List;

public class PGActivityDAO extends PGBaseDAO<Activity> implements IActivityDAO {


    public PGActivityDAO(HibernateDataSource dataSource, Class<Activity> clazz) {
        super(dataSource, clazz);
    }


    @Override
    public List<Activity> getAllActivitiesByUserAndBuilding(User user, Building building) throws DAOAppException {
        String hql = "SELECT act FROM Activity as act INNER JOIN act.building bld INNER JOIN bld.report rpt INNER JOIN rpt.user usr WHERE usr = :user AND bld = :building";
        try {
            return session.createQuery(hql)
                    .setParameter("user", user).setParameter("building", building)
                    .list();
        } catch (HibernateException e) {
            throw new DAOAppException(e.getMessage());
        }
    }

    @Override
    public Double getTotalActivitiesPriceByBuilding(Building building) throws DAOAppException {
        try {
            return (Double) session.createCriteria(clazz)
                    .createAlias("building", "bld")
                    .add(Restrictions.eq("bld.id", building.getBuildingId()))
                    .setProjection(Projections.sum("price")).uniqueResult();
        } catch (HibernateException e) {
            throw new DAOAppException(e.getMessage());
        }
    }

    @Override
    public Double getTotalActivitiesPriceByUser(User user) throws DAOAppException {
        try {
            return (Double) session.createCriteria(clazz, "act")
                    .createAlias("act.building", "bld")
                    .createAlias("bld.report", "rpt")
                    .createAlias("rpt.user", "usr")
                    .add(Restrictions.eq("usr.id", user.getUserId()))
                    .setProjection(Projections.sum("act.price")).uniqueResult();
        } catch (HibernateException e) {
            throw new DAOAppException(e.getMessage());
        }
    }

    @Override
    public Double getTotalActivitiesPriceByReport(Report report) throws DAOAppException {
        try {
            return (Double) session.createCriteria(clazz, "act")
                    .createAlias("act.building", "bld")
                    .createAlias("bld.report", "rpt")
                    .add(Restrictions.eq("rpt.id", report.getReportId()))
                    .setProjection(Projections.sum("act.price")).uniqueResult();
        } catch (HibernateException e) {
            throw new DAOAppException(e.getMessage());
        }
    }
}
