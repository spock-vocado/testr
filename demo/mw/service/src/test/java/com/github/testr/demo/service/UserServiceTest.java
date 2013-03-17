package com.github.testr.demo.service;

import com.github.testr.demo.dal.entity.User;
import com.github.testr.demo.dal.fixture.DefaultFixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

@ContextConfiguration(locations = "classpath:test-context-service.xml")
public class UserServiceTest extends AbstractServiceTest {

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