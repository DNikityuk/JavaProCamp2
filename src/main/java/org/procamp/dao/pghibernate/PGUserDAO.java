package org.procamp.dao.pghibernate;

import org.procamp.dao.IUserDAO;
import org.procamp.model.HibernateDataSource;
import org.procamp.model.entity.User;

public class PGUserDAO extends PGBaseDAO<User> implements IUserDAO {


    public PGUserDAO(HibernateDataSource dataSource, Class<User> clazz) {
        super(dataSource, clazz);
    }
}
