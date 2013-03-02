package com.github.testr.builder;

import com.github.testr.builder.jpa.AbstractJpaObjectHandler;
import com.github.testr.builder.pojos.AddressBuilder;
import com.github.testr.builder.pojos.Person;
import com.github.testr.builder.pojos.PersonBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;


public class BuilderFactoryTest {

    private DynBuilderFactory bf;

    @BeforeMethod
    public void init() {
        bf = new DynBuilderFactory();
        bf.setHandler(new AbstractJpaObjectHandler() {
            @Override
            protected Object persist(Object o) {
                log.debug("Persisting " + o);
                return o;
            }
        });
    }

    @Test(expectedExceptions = BuilderException.class, expectedExceptionsMessageRegExp = "Incorrect API usage.+")
    public void testDependent() {
        bf.get(AddressBuilder.class)
                .address1("285 Bay st")
                .city("Long Beach")
                .state("CA")
                .country("USA")
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
