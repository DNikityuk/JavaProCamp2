package org.procamp.dao;

import org.procamp.exception.DAOAppException;
import org.procamp.model.entity.Report;
import org.procamp.model.entity.User;

import java.util.List;

public interface IReportDAO extends IBaseDAO<Report> {

    List<Report> getAllReportsByUser(User user) throws DAOAppException;
}
