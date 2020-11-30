package org.procamp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.procamp.dao.IReportDAO;
import org.procamp.dao.IUserDAO;
import org.procamp.exception.AppException;
import org.procamp.exception.DAOAppException;
import org.procamp.factory.DAOFactory;
import org.procamp.model.entity.Report;
import org.procamp.model.entity.User;

import java.util.ArrayList;
import java.util.List;


public class ReportService extends BaseService {

    private static final Logger logger = LogManager.getLogger(ReportService.class);

    public List<Report> getAllReportsByUserId(Long userId, DAOFactory externalDaoFactory) {
        DAOFactory daoFactory = getDAOFactory(externalDaoFactory);
        try {

            final IReportDAO reportDAO = daoFactory.getReportDAO();
            final IUserDAO userDAO = daoFactory.getUserDAO();

            User user = userDAO.getById(userId);
            if (user == null) {
                throw new AppException("Doesn't have user for current userId");
            }

            return reportDAO.getAllReportsByUser(user);
        } catch (DAOAppException e) {
            logger.error("DAOAppException in getAllReportsByUserId: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception in getAllReportsByUserId: " + e.getMessage(), e);
        } finally {
            super.finalizeConnectionIfNeed(daoFactory, externalDaoFactory, logger);
        }
        return new ArrayList<>();
    }
}
