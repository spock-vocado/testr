package com.github.testr.sample.repository;

import com.github.testr.sample.AbstractTransactionalTest;
import com.github.testr.sample.fixture.DefaultFixture;
import com.github.testr.sample.model.testr.User;
import com.github.testr.sample.repository.testr.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class UserRepositoryTest extends AbstractTransactionalTest {

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