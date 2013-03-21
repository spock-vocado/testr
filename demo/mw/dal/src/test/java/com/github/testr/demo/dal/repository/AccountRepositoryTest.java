package com.github.testr.demo.dal.repository;

import com.github.testr.demo.dal.entity.Account;
import com.github.testr.demo.dal.entity.AccountType;
import com.github.testr.demo.dal.entity.Book;
import com.github.testr.demo.dal.fixture.DefaultFixture;
import org.fest.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.util.Lists.newArrayList;

@ContextConfiguration(locations = "classpath:test-context-dal.xml")
public class AccountRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private DefaultFixture defaultFixture;

    @BeforeMethod
    public void init() {
        defaultFixture.run();
    }

    @Test
    public void testFind() {
        List<Account> list = newArrayList(repository.findAll());
        assertThat(list).hasSize(4);
        assertThat(list.get(0).getType()).isNotNull();
        assertThat(list.get(0).getType()).isInstanceOf(AccountType.class);
    }

}