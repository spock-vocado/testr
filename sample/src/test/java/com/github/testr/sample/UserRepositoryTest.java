package com.github.testr.sample;

import com.github.testr.sample.fixture.Fixture;
import com.github.testr.sample.model.User;
import com.github.testr.sample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.fest.assertions.api.Assertions.assertThat;

@ContextConfiguration(locations = "classpath:test-context-sample.xml")
@Transactional
public class UserRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private UserRepository repository;

    @PersistenceContext
    private EntityManager em;

    private Fixture fixture;

    @BeforeMethod
    public void fixture() {
        fixture = new Fixture(em);
    }

    @Test
    public void testFind() {
        System.out.println(fixture.user1);

        User u = repository.findByUsername(fixture.user1.getUsername());
        assertThat(u).isNotNull();
        assertThat(u.getAddress()).isNotNull();
        assertThat(u.getAddress().getUser()).isNotNull();

        assertThat(repository.count()).isEqualTo(2);
    }


}
