package org.procamp.dao.pghibernate;

import org.procamp.exception.DAOAppException;
import org.hibernate.HibernateException;
import org.procamp.dao.IReportDAO;
import org.procamp.model.HibernateDataSource;
import org.procamp.model.entity.Report;
import org.procamp.model.entity.User;

import java.util.List;

public class PGReportDAO extends PGBaseDAO<Report> implements IReportDAO {


    public PGReportDAO(HibernateDataSource dataSource, Class<Report> clazz) {
        super(dataSource, clazz);
    }


    @Override
    public List<Report> getAllReportsByUser(User user) throws DAOAppException {
        String sql = "SELECT rpt.* FROM public.report rpt INNER JOIN public.user usr ON rpt.user_id = usr.inst_id WHERE usr.inst_id = :userId";
        try {
            return session.createSQLQuery(sql).addEntity(clazz)
                    .setParameter("userId", user.getUserId())
                    .list();
        } catch (HibernateException e) {
            throw new DAOAppException(e.getMessage());
        }
    }
}
