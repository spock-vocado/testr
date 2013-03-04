package com.github.testr.sample.service;

import com.github.testr.sample.AbstractTransactionalTest;
import com.github.testr.sample.fixture.DefaultFixture;
import com.github.testr.sample.model.testr.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class UserServiceTest extends AbstractTransactionalTest {

    @Autowired
    private UserService userService;

    @Autowired
    private DefaultFixture defaultFixture;

    @BeforeMethod
    public void init() {
        defaultFixture.run();
    }

    @Test
    public void testFind() {
        User u = userService.newUser("foo", "foo@domain.com");
        flush();
        assertThat(u).isNotNull();
        assertThat(u.getId()).isNotNull();
    }

}