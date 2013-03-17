package com.github.testr.sample.dal.repository;

import com.github.testr.sample.dal.fixture.EnumTypeBuilder;
import com.github.testr.sample.dal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

@ContextConfiguration(locations = "classpath:test-context-dal.xml")
public class EnumTypeRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private EnumTypeRepository repository;

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
