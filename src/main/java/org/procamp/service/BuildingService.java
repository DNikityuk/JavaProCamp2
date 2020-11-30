package org.procamp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.procamp.dao.IBuildingDAO;
import org.procamp.exception.DAOAppException;
import org.procamp.factory.DAOFactory;


public class BuildingService extends BaseService {

    private static final Logger logger = LogManager.getLogger(BuildingService.class);


    public void deactivateBuildingsWitchTotalPriceActivitiesMoreThan(Double totalPrice, DAOFactory externalDaoFactory) {
        DAOFactory daoFactory = getDAOFactory(externalDaoFactory);
        try {
            final IBuildingDAO buildingDAO = daoFactory.getBuildingDAO();
            super.startTransactionIfNeed(daoFactory, externalDaoFactory, logger);

            buildingDAO.updateBuildingStatusByActivitiesTotalPriceMoreThan(false, totalPrice);

            super.commitTransactionIfNeed(daoFactory, externalDaoFactory, logger);
        } catch (DAOAppException e) {
            super.rollbackTransactionIfNeed(daoFactory, externalDaoFactory, logger);
            logger.error("DAOAppException in deactivateBuildingsWitchTotalPriceActivitiesMoreThan: "+e.getMessage(), e);
        } catch (Exception e) {
            super.rollbackTransactionIfNeed(daoFactory, externalDaoFactory, logger);
            logger.error("Exception in deactivateBuildingsWitchTotalPriceActivitiesMoreThan: "+e.getMessage(), e);
        } finally {
            super.finalizeConnectionIfNeed(daoFactory, externalDaoFactory, logger);
        }
    }


}
