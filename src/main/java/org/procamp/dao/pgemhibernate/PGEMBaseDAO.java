package org.procamp.dao.pgemhibernate;

import org.procamp.dao.IBaseDAO;
import org.procamp.model.EntityManagerDataSource;

import javax.persistence.EntityManager;
import java.util.List;

public class PGEMBaseDAO<T> implements IBaseDAO<T> {


    protected EntityManagerDataSource emDataSource;
    protected EntityManager entityManager;
    protected Class<T> clazz;

    public PGEMBaseDAO(EntityManagerDataSource emDataSource, Class<T> clazz) {
        this.emDataSource = emDataSource;
        entityManager = emDataSource.getEntityManager();
        this.clazz = clazz;
    }

    @Override
    public T getById(Long id) {
        if (id == null) return null;
        return entityManager.find(clazz, id);
    }

    @Override
    public void save(T entity) {
        entityManager.persist(entity);

    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public void refresh(T entity) {
        if (entity == null) return;
        entityManager.refresh(entity);
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
        entityManager.remove(entity);
    }

    @Override
    public void deleteById(Long entityId) {
        T entity = getById(entityId);
        if (entity != null) {
            delete(entity);
        }
    }
}
