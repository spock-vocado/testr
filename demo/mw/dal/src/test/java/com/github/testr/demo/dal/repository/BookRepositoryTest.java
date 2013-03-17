package com.github.testr.demo.dal.repository;

import com.github.testr.demo.dal.entity.Book;
import com.github.testr.demo.dal.fixture.DefaultFixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

@ContextConfiguration(locations = "classpath:test-context-dal.xml")
public class BookRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private DefaultFixture defaultFixture;

    @BeforeMethod
    public void init() {
        defaultFixture.run();
    }

    @Test
    public void testFind() {
        System.out.println(defaultFixture.book1);
        Book b = repository.findByNameAndUser(defaultFixture.book1.getName(), defaultFixture.user1);
        assertThat(b).isNotNull();
        assertThat(b.getName()).isEqualTo(defaultFixture.book1.getName());
        assertThat(repository.count()).isEqualTo(2);
    }

    @Test
    public void testUniqueKey() {
        Book b = new Book();
        b.setName("book");
        b.setUser(defaultFixture.user1);
        repository.save(b);
        assertThat(b.getId()).isNotNull();

        // same name, different user
        b = new Book();
        b.setName("book");
        b.setUser(defaultFixture.user2);
        repository.save(b);
        assertThat(b.getId()).isNotNull();

        // same name, same user => MUST FAIL
        b = new Book();
        b.setName("book");
        b.setUser(defaultFixture.user1);
        try {
            repository.save(b);
            Assert.fail("Should fail");
        } catch (DataIntegrityViolationException e) {
            //ok
        }
    }

}