package com.github.testr.sample;

import com.github.testr.builder.BuilderFactory;
import com.github.testr.builder.jpa.JpaBuilderHandler;
import com.github.testr.sample.fixture.AddressBuilder;
import com.github.testr.sample.fixture.UserBuilder;
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

import static com.github.testr.builder.BuilderHelper.begin;
import static com.github.testr.builder.BuilderHelper.setDefaultBuilderFactory;
import static org.fest.assertions.api.Assertions.assertThat;

@ContextConfiguration(locations = "classpath:context-sample.xml")
@Transactional
public class UserRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private UserRepository repository;

    @PersistenceContext
    private EntityManager em;

    private User u;

    @BeforeMethod
    public void fixture() {
        setDefaultBuilderFactory(new BuilderFactory(new JpaBuilderHandler(em)));

        u = begin(UserBuilder.class)
                .username("user")
                .firstName("FirstName")
                .lastName("LastName")
                .email("user@domain.com")
                .address(begin(AddressBuilder.class)
                        .address1("666 Main St.")
                        .city("Long Beach")
                        .state("CA")
                        .country("USA"))
                .build();
    }

    @Test
    public void testFind() {
        for (User u : repository.findAll()) {
            System.out.println(u);
            assertThat(u.getAddress()).isNotNull();
            assertThat(u.getAddress().getUser()).isNotNull();
            assertThat(u.getAddress().getUser()).isSameAs(u);
        }
        assertThat(repository.count()).isEqualTo(1);
    }


}
