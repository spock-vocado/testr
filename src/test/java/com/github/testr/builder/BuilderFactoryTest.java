package com.github.testr.builder;

import com.github.testr.builder.builders.InvalidPersonBuilder;
import com.github.testr.builder.builders.PersonBuilder;
import com.github.testr.builder.builders.PhoneBuilder;
import com.github.testr.builder.jpa.AbstractJpaBuilderHandler;
import com.github.testr.builder.builders.AddressBuilder;
import com.github.testr.builder.pojos.Person;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;


public class BuilderFactoryTest {

    private BuilderFactory bf;

    @BeforeMethod
    public void init() {
        bf = new BuilderFactory();
        bf.setHandler(new AbstractJpaBuilderHandler() {
            @Override
            protected Object persist(Object o) {
                log.debug("Persisting " + o);
                return o;
            }
        });
    }

    @Test(expectedExceptions = BuilderException.class,
            expectedExceptionsMessageRegExp = "Incorrect API usage.+")
    public void testMissingParent() {
        bf.get(AddressBuilder.class)
                .address1("285 Bay st")
                .city("Long Beach")
                .state("CA")
                .country("USA")
                .build();
    }

    @Test(expectedExceptions = BuilderException.class,
            expectedExceptionsMessageRegExp = "Could not find field 'weight'.+\\.Person")
    public void testUndefinedField() {
        bf.get(InvalidPersonBuilder.class)
                .firstName("Antonio")
                .lastName("Gomes")
                .weight(999)
                .build();
    }

    @Test
    public void testBuilder() {
        Person p = bf.get(PersonBuilder.class)
                .firstName("Antonio")
                .ssn("00000000")
                .active(true)
                .dob("1969-01-01")
                .address(bf.get(AddressBuilder.class)
                        .address1("285 Bay st")
                        .city("Long Beach")
                        .state("CA")
                        .country("USA")
                        .build())
                .build();

        System.out.println("RESULT = " + p);

        assertThat(p.getFirstName()).isEqualTo("Antonio");
        assertThat(p.getActive()).isEqualTo(true);
        assertThat(p.getAddress()).isNotNull();
        assertThat(p.getAddress().getAddress1()).isEqualTo("285 Bay st");
        assertThat(p.getDob()).isEqualTo("1969-01-01");
    }

}
