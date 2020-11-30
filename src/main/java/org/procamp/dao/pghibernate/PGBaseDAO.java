package org.procamp.dao.pghibernate;

import org.hibernate.Session;
import org.procamp.dao.IBaseDAO;
import org.procamp.model.HibernateDataSource;

import java.util.List;

public class PGBaseDAO<T> implements IBaseDAO<T> {

    protected HibernateDataSource dataSource;
    protected Session session;
    protected Class<T> clazz;

    public PGBaseDAO(HibernateDataSource dataSource, Class<T> clazz) {
        this.dataSource = dataSource;
        session = dataSource.getSession();
        this.clazz = clazz;
    }

    @Override
    public T getById(Long id) {
        if (id == null) return null;
        return (T) session.get(clazz, id);
    }

    @Override
    public void save(T entity) {
        session.persist(entity);
    }

    @Override
    public T update(T entity) {
        return (T) session.merge(entity);
    }

    @Override
    public void refresh(T entity) {
        if (entity == null) return;
        session.refresh(entity);
    }

    @Override
    public void refreshAll(List<T> entities) {
        if (entities == null) return;

        for (T entity : entities) {
            refresh(entity);
        }
    }

    @Override
    public void delete(T entity) {
        session.delete(entity);
    }

    @Override
    public void deleteById(Long entityId) {
        T entity = getById(entityId);
        if (entity != null){
            delete(entity);
        }
    }
}
