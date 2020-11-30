package org.procamp.dao;

import java.util.List;

public interface IBaseDAO<T> {

    T getById(Long id);

    void save(T entity);

    T update(T entity);

    void refresh(T entity);

    void refreshAll(List<T> entities);

    void delete(T entity);

    void deleteById(Long entityId);
}
