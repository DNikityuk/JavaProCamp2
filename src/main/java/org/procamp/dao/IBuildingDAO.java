package org.procamp.dao;

import org.procamp.exception.DAOAppException;
import org.procamp.model.entity.Building;

public interface IBuildingDAO extends IBaseDAO<Building> {

    int updateBuildingStatusByActivitiesTotalPriceMoreThan(boolean status, double specifiedPrice) throws DAOAppException;
}
