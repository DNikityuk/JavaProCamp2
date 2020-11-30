package org.procamp.dao.pgemhibernate;

import org.hibernate.HibernateException;
import org.procamp.dao.IBuildingDAO;
import org.procamp.exception.DAOAppException;
import org.procamp.model.EntityManagerDataSource;
import org.procamp.model.entity.Building;

public class PGEMBuildingDAO extends PGEMBaseDAO<Building> implements IBuildingDAO {

    public PGEMBuildingDAO(EntityManagerDataSource emDataSource, Class<Building> clazz) {
        super(emDataSource, clazz);
    }

    @Override
    public int updateBuildingStatusByActivitiesTotalPriceMoreThan(boolean status, double specifiedPrice) throws DAOAppException {
        String hql = "UPDATE Building SET active = :status WHERE buildingId IN (SELECT building.buildingId FROM Activity GROUP BY building.buildingId HAVING sum(price) > :price)";

        try {
            return entityManager.createQuery(hql)
                    .setParameter("status", status).setParameter("price", specifiedPrice)
                    .executeUpdate();
        } catch (HibernateException e) {
            throw new DAOAppException(e.getMessage());
        }

    }
}
