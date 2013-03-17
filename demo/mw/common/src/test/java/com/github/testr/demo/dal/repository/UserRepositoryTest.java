package com.github.testr.demo.dal.repository;

import com.github.testr.demo.dal.fixture.DefaultFixture;
import com.github.testr.demo.dal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

@ContextConfiguration(locations = "classpath:test-context-dal.xml")
public class UserRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Autowired
    private DefaultFixture defaultFixture;

    @BeforeMethod
    public void init() {
        defaultFixture.run();
    }

    @Test
    public void testFind() {
        System.out.println(defaultFixture.user1);

        User u = repository.findByUsername(defaultFixture.user1.getUsername());
        assertThat(u).isNotNull();
        assertThat(u.getAddress()).isNotNull();
        assertThat(u.getAddress().getUser()).isNotNull();

        assertThat(repository.count()).isEqualTo(2);
    }

}