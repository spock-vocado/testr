package com.github.testr.builder;

import com.github.testr.builder.extra.AddressBuilder;
import com.github.testr.builder.extra.Person;
import com.github.testr.builder.extra.PersonBuilder;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;


public class BuilderFactoryTest {

    @Test
    public void testBuilder() {

        IBuilderFactory f = new DynBuilderFactory();

        Person p = f.create(PersonBuilder.class)
                .firstName("Antonio")
                .ssn("00000000")
                .active(true)
                .address(f.create(AddressBuilder.class)
                        .address1("285 Bay st")
                        .city("Long Beach")
                        .state("CA")
                        .country("USA"))
                .build();

        System.out.println("RESULT = " + p);

        assertThat(p.getFirstName()).isEqualTo("Antonio");
        assertThat(p.getActive()).isEqualTo(true);
        assertThat(p.getAddress()).isNotNull();
        assertThat(p.getAddress().getAddress1()).isEqualTo("285 Bay st");
    }

}
