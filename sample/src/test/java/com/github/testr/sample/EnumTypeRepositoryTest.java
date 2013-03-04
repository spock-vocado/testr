package com.github.testr.sample;

import com.github.testr.sample.model.EnumType;
import com.github.testr.sample.model.User;
import com.github.testr.sample.repository.EnumTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

@ContextConfiguration(locations = "classpath:test-context-sample.xml")
@Transactional
public class EnumTypeRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private EnumTypeRepository repository;

    @PersistenceContext
    private EntityManager em;

    @BeforeMethod
    public void fixture() {
        new EnumTypeBuilder(em)
                .name("ADDRESS_TYPE")
                .value("PERMANENT")
                .value("BUSINESS")
                .value("OTHER")
                .build();
        new EnumTypeBuilder(em)
                .name("DEPENDENCY_TYPE")
                .value("DEPENDENT")
                .value("INDEPENDENT")
                .build();
        new EnumTypeBuilder(em)
                .name("PHONE_TYPE")
                .value("MOBILE")
                .value("OFFICE")
                .value("OTHER")
                .build();
        new EnumTypeBuilder(em)
                .name("ENROLLMENT_TYPE")
                .value("APPLICANT")
                .value("ACTIVE")
                .build();
    }

    @Test
    public void testFind() {
        assertThat(repository.count()).isEqualTo(4);
        List<User> v = repository.findByName("ADDRESS_TYPE");
        assertThat(v).isNotNull();
    }


}
