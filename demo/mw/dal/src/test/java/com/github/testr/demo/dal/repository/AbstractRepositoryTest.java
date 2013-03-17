package com.github.testr.demo.dal.repository;

import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public abstract class AbstractRepositoryTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    protected EntityManager em;

    protected void flush() {
        em.flush();
    }
}