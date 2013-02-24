package com.github.testbed;

import com.github.testbed.builder.BuilderFactory;
import com.github.testbed.entity.Person;
import com.github.testbed.entity.builder.AddressBuilder;
import com.github.testbed.entity.builder.PersonBuilder;


public class FooTest {

    public static void main(String[] args) {

        BuilderFactory f = new BuilderFactory();

        Person p = f.create(PersonBuilder.class)
                .firstName("Antonio")
                .lastName("Gomes")
                .dob("1950-01-01")
                .ssn("00000000")
                .address(f.create(AddressBuilder.class)
                        .address1("285 Bay st")
                        .city("Long Beach")
                        .state("CA")
                        .country("USA"))
                .build();

        System.out.println("RESULT = " + p);

    }

}
