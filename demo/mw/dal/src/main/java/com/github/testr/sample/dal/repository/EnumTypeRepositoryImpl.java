package com.github.testr.sample.dal.repository;

import com.github.testr.sample.dal.entity.EnumType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EnumTypeRepositoryImpl implements EnumTypeRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    public void refresh(EnumType entity) {
        em.refresh(entity);
    }

}
