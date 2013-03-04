package com.github.testr.sample;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@TestExecutionListeners(TransactionalTestExecutionListener.class)

@ContextConfiguration(locations = "classpath:test-context-sample.xml")
@Transactional
public abstract class AbstractTransactionalTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext(unitName = "testrPu")
    protected EntityManager testrEm;

    @PersistenceContext(unitName = "otherPu")
    protected EntityManager otherEm;

    protected void flush() {
        testrEm.flush();
        otherEm.flush();
    }
}