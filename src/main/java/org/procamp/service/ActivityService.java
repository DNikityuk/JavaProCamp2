package org.procamp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.procamp.dao.IActivityDAO;
import org.procamp.dao.IBuildingDAO;
import org.procamp.dao.IReportDAO;
import org.procamp.dao.IUserDAO;
import org.procamp.exception.AppException;
import org.procamp.factory.DAOFactory;
import org.procamp.model.entity.Activity;
import org.procamp.model.entity.Building;
import org.procamp.model.entity.Report;
import org.procamp.model.entity.User;
import org.procamp.exception.DAOAppException;

import java.util.ArrayList;
import java.util.List;


public class ActivityService extends BaseService {

    private static Logger logger = LogManager.getLogger(ActivityService.class);


    public List<Activity> getAllActivitiesByUserIdAndBuildingId(Long userId, Long buildingId, DAOFactory externalDaoFactory) {
        DAOFactory daoFactory = getDAOFactory(externalDaoFactory);

        try {

            final IActivityDAO activityDAO = daoFactory.getActivityDAO();
            final IBuildingDAO buildingDAO = daoFactory.getBuildingDAO();
            final IUserDAO userDAO = daoFactory.getUserDAO();

            User user = userDAO.getById(userId);
            if (user == null) {
                throw new AppException("Doesn't have user for current userId");
            }
            Building building = buildingDAO.getById(buildingId);
            if (building == null) {
                throw new AppException("Doesn't have building for current buildingId");
            }

            return activityDAO.getAllActivitiesByUserAndBuilding(user, building);
        } catch (DAOAppException e) {
            logger.error("DAOAppException in getAllActivitiesByUserIdAndBuildingId: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception in getAllActivitiesByUserIdAndBuildingId: " + e.getMessage(), e);
        } finally {
            super.finalizeConnectionIfNeed(daoFactory, externalDaoFactory, logger);
        }
        return new ArrayList<>();
    }

    public Double getTotalActivitiesPriceByBuildingId(Long buildingId, DAOFactory externalDaoFactory) {
        DAOFactory daoFactory = getDAOFactory(externalDaoFactory);

        try {

            final IActivityDAO activityDAO = daoFactory.getActivityDAO();
            final IBuildingDAO buildingDAO = daoFactory.getBuildingDAO();

            Building building = buildingDAO.getById(buildingId);

            if (building == null) {
                throw new AppException("Doesn't have building for current buildingId");
            }

            return activityDAO.getTotalActivitiesPriceByBuilding(building);
        } catch (DAOAppException e) {
            logger.error("DAOAppException in getTotalActivitiesPriceByBuildingId: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception in getTotalActivitiesPriceByBuildingId: " + e.getMessage(), e);
        } finally {
            super.finalizeConnectionIfNeed(daoFactory, externalDaoFactory, logger);
        }
        return null;
    }

    public Double getTotalActivitiesPriceByReportId(Long reportId, DAOFactory externalDaoFactory) {
        DAOFactory daoFactory = getDAOFactory(externalDaoFactory);
        try {

            final IActivityDAO activityDAO = daoFactory.getActivityDAO();
            final IReportDAO reportDAO = daoFactory.getReportDAO();

            Report report = reportDAO.getById(reportId);
            if (report == null) {
                throw new AppException("Doesn't have report for current reportId");
            }

            return activityDAO.getTotalActivitiesPriceByReport(report);
        } catch (DAOAppException e) {
            logger.error("DAOAppException in getTotalActivitiesPriceByReportId: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception in getTotalActivitiesPriceByReportId: " + e.getMessage(), e);
        } finally {
            super.finalizeConnectionIfNeed(daoFactory, externalDaoFactory, logger);
        }
        return null;
    }


    public Double getTotalActivitiesPriceByUserId(Long userId, DAOFactory externalDaoFactory) {
        DAOFactory daoFactory = getDAOFactory(externalDaoFactory);

        try {

            final IActivityDAO activityDAO = daoFactory.getActivityDAO();
            final IUserDAO userDAO = daoFactory.getUserDAO();

            User user = userDAO.getById(userId);
            if (user == null) {
                throw new AppException("Doesn't have user for current userId");
            }

            return activityDAO.getTotalActivitiesPriceByUser(user);
        } catch (DAOAppException e) {
            logger.error("DAOAppException in getTotalActivitiesPriceByUserId: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception in getTotalActivitiesPriceByUserId: " + e.getMessage(), e);
        } finally {
            super.finalizeConnectionIfNeed(daoFactory, externalDaoFactory, logger);
        }
        return null;
    }


}
