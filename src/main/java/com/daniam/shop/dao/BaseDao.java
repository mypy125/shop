package com.daniam.shop.dao;

import com.daniam.shop.domain.BaseEntity;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseDao<K extends Serializable, E extends BaseEntity<K>> implements Dao<K, E> {
    private final SessionFactory sessionFactory;
    private final Class<E> clazz;

    public BaseDao(SessionFactory sessionFactory, Class<E> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    @Override
    public E save(E entity) {
        var session = sessionFactory.getCurrentSession();
        session.save(entity);
        return entity;
    }

    @Override
    public void delete(K id) {
        var session = sessionFactory.getCurrentSession();
        session.delete(id);
        session.flush();
    }

    @Override
    public void update(E entity) {
        var session = sessionFactory.getCurrentSession();
        session.merge(entity);
    }

    @Override
    public Optional<E> findById(K id) {
        var session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.find(clazz, id));
    }

    @Override
    public List<E> findAll() {
        var session = sessionFactory.getCurrentSession();
        var criteria = session.getCriteriaBuilder().createQuery(clazz);
        criteria.from(clazz);
        return session.createQuery(criteria)
                .getResultList();
    }
}
