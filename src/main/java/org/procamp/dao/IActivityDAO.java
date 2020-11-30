package org.procamp.dao;

import org.procamp.exception.DAOAppException;
import org.procamp.model.entity.Activity;
import org.procamp.model.entity.Building;
import org.procamp.model.entity.Report;
import org.procamp.model.entity.User;

import java.util.List;

public interface IActivityDAO extends IBaseDAO<Activity> {

    List<Activity> getAllActivitiesByUserAndBuilding(User user, Building building) throws DAOAppException;

    Double getTotalActivitiesPriceByBuilding(Building building) throws DAOAppException;

    Double getTotalActivitiesPriceByUser(User user) throws DAOAppException;

    Double getTotalActivitiesPriceByReport(Report report) throws DAOAppException;
}
