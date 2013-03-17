package com.github.testr.demo.dal.repository;

import com.github.testr.demo.dal.entity.EnumType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EnumTypeRepositoryImpl implements EnumTypeRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    public void refresh(EnumType entity) {
        em.refresh(entity);
    }

}
