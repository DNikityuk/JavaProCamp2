package org.procamp.dao.pgemhibernate;

import org.procamp.dao.IUserDAO;
import org.procamp.model.EntityManagerDataSource;
import org.procamp.model.entity.User;

public class PGEMUserDAO extends PGEMBaseDAO<User> implements IUserDAO {

    public PGEMUserDAO(EntityManagerDataSource emDataSource, Class<User> clazz) {
        super(emDataSource, clazz);
    }
}
