package org.procamp.dao.pgemhibernate;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.procamp.dao.IActivityDAO;
import org.procamp.exception.DAOAppException;
import org.procamp.model.EntityManagerDataSource;
import org.procamp.model.entity.Activity;
import org.procamp.model.entity.Building;
import org.procamp.model.entity.Report;
import org.procamp.model.entity.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class PGEMActivityDAO extends PGEMBaseDAO<Activity> implements IActivityDAO {

    public PGEMActivityDAO(EntityManagerDataSource emDataSource, Class<Activity> clazz) {
        super(emDataSource, clazz);
    }

    @Override
    public List<Activity> getAllActivitiesByUserAndBuilding(User user, Building building) throws DAOAppException {
        String hql = "SELECT act FROM Activity as act INNER JOIN act.building bld INNER JOIN bld.report rpt INNER JOIN rpt.user usr WHERE usr = :user AND bld = :building";
        try {
            return entityManager.createQuery(hql)
                    .setParameter("user", user).setParameter("building", building)
                    .getResultList();
        } catch (HibernateException e) {
            throw new DAOAppException(e.getMessage());
        }
    }

    @Override
    public Double getTotalActivitiesPriceByBuilding(Building building) throws DAOAppException {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Double> criteriaQuery = cb.createQuery(Double.class);
            Root<Activity> activity = criteriaQuery.from(Activity.class);
            Join<Activity, Building> joinBuilding = activity.join("building");

            criteriaQuery.select(cb.sum(activity.get("price"))).where(cb.equal(joinBuilding.get("buildingId"), building.getBuildingId()));

            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (HibernateException e) {
            throw new DAOAppException(e.getMessage());
        }
    }

    @Override
    public Double getTotalActivitiesPriceByUser(User user) throws DAOAppException {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Double> criteriaQuery = cb.createQuery(Double.class);
            Root<Activity> activity = criteriaQuery.from(Activity.class);
            Join<Activity, Building> joinBuilding = activity.join("building");
            Join<Building, Report> joinReport = joinBuilding.join("report");
            Join<Report, User> joinUser = joinReport.join("user");

            criteriaQuery.select(cb.sum(activity.get("price"))).where(cb.equal(joinUser.get("userId"), user.getUserId()));

            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (HibernateException e) {
            throw new DAOAppException(e.getMessage());
        }
    }

    @Override
    public Double getTotalActivitiesPriceByReport(Report report) throws DAOAppException {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Double> criteriaQuery = cb.createQuery(Double.class);
            Root<Activity> activity = criteriaQuery.from(Activity.class);
            Join<Activity, Building> joinBuilding = activity.join("building");
            Join<Building, Report> joinReport = joinBuilding.join("report");

            criteriaQuery.select(cb.sum(activity.get("price"))).where(cb.equal(joinReport.get("reportId"), report.getReportId()));

            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (HibernateException e) {
            throw new DAOAppException(e.getMessage());
        }
    }
}
