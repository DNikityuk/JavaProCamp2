package org.procamp.dao.pgemhibernate;

import org.hibernate.HibernateException;
import org.procamp.dao.IReportDAO;
import org.procamp.exception.DAOAppException;
import org.procamp.model.EntityManagerDataSource;
import org.procamp.model.entity.Report;
import org.procamp.model.entity.User;

import java.util.List;

public class PGEMReportDAO extends PGEMBaseDAO<Report> implements IReportDAO {


    public PGEMReportDAO(EntityManagerDataSource emDataSource, Class<Report> clazz) {
        super(emDataSource, clazz);
    }

    @Override
    public List<Report> getAllReportsByUser(User user) throws DAOAppException {
        String sql = "SELECT rpt.* FROM public.report rpt INNER JOIN public.user usr ON rpt.user_id = usr.inst_id WHERE usr.inst_id = :userId";
        try {
            return entityManager.createNativeQuery(sql, clazz)
                    .setParameter("userId", user.getUserId())
                    .getResultList();
        } catch (HibernateException e) {
            throw new DAOAppException(e.getMessage());
        }
    }
}
