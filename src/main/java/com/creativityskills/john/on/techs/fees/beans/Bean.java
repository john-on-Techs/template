package com.creativityskills.john.on.techs.fees.beans;

import org.jboss.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@Local
public class Bean<T> implements BeanI<T> {
    @Inject
    private Logger log;
    @PersistenceContext(name = "pu")
    protected EntityManager entityManager;


    protected Class<T> entityClass;
    @Override
    public T create(T t) {
         entityManager.persist(t);
         log.info("Created: "+entityClass.getName()+" to database");
         return t;
    }

    @Override
    public T read(long id) {
        return entityManager.find(entityClass,id);
    }

    @Override
    public T update(T t) {
        entityManager.merge(t);
        log.info("Updated: "+entityClass.getName()+" to database");
        return t;
    }

    @Override
    public T delete(T t) {
       entityManager.remove(t);
        log.info("Deleted: "+entityClass.getName()+" to database");

        return t;
    }
}
