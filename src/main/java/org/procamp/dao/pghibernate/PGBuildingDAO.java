package org.procamp.dao.pghibernate;

import org.procamp.exception.DAOAppException;
import org.hibernate.HibernateException;
import org.procamp.dao.IBuildingDAO;
import org.procamp.model.HibernateDataSource;
import org.procamp.model.entity.Building;

public class PGBuildingDAO extends PGBaseDAO<Building> implements IBuildingDAO {


    public PGBuildingDAO(HibernateDataSource dataSource, Class<Building> clazz) {
        super(dataSource, clazz);
    }

    @Override
    public int updateBuildingStatusByActivitiesTotalPriceMoreThan(boolean status, double specifiedPrice) throws DAOAppException {
        String sql = "UPDATE building SET is_active = :status " +
                "WHERE inst_id IN (SELECT building_id FROM activity act GROUP BY building_id HAVING sum(act.price) > :price)";

        try {
            return session.createSQLQuery(sql)
                    .setParameter("status", status).setParameter("price", specifiedPrice)
                    .executeUpdate();
        } catch (HibernateException e) {
            throw new DAOAppException(e.getMessage());
        }

    }
}
