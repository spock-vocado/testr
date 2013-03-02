package com.github.testr.builder;

import com.github.testr.builder.builders.InvalidPersonBuilder;
import com.github.testr.builder.builders.PersonBuilder;
import com.github.testr.builder.builders.PhoneBuilder;
import com.github.testr.builder.builders.ProductBuilder;
import com.github.testr.builder.jpa.AbstractJpaBuilderHandler;
import com.github.testr.builder.builders.AddressBuilder;
import com.github.testr.builder.pojos.Person;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.github.testr.builder.BuilderHelper.begin;
import static org.fest.assertions.api.Assertions.assertThat;


public class BuilderFactoryTest {

    @BeforeMethod
    public void init() {
        BuilderFactory factory = new BuilderFactory();
        factory.setHandler(new AbstractJpaBuilderHandler() {
            @Override
            protected Object persist(Object o) {
                log.debug("Persisting " + o);
                return o;
            }
        });
        BuilderHelper.setFactory(factory);
    }

    @Test(expectedExceptions = BuilderException.class,
            expectedExceptionsMessageRegExp = "Incorrect API usage.+")
    public void testMissingParent() {
        // this should fail because AddressBuilder declared @ChildOf(Person.class)
        begin(AddressBuilder.class)
                .address1("285 Bay st")
                .city("Long Beach")
                .state("CA")
                .country("USA")
                .build();
    }

    @Test(expectedExceptions = BuilderException.class,
            expectedExceptionsMessageRegExp = "Incorrect API usage: current object must " +
                    "be defined in the context of an instance of .+\\.Person")
    public void testIncorrectParent() {

        // notice that we haven't invoked build(), so a product instance is still in the context
        begin(ProductBuilder.class)
                .name("spyglass")
                .quantity(10);

        // this should fail because AddressBuilder declared @ChildOf(Person.class)
        begin(AddressBuilder.class)
                .address1("285 Bay st")
                .city("Long Beach")
                .state("CA")
                .country("USA")
                .build();
    }

    @Test(expectedExceptions = BuilderException.class,
            expectedExceptionsMessageRegExp = "Could not find field 'weight'.+\\.Person")
    public void testUndefinedField() {
        begin(InvalidPersonBuilder.class)
                .firstName("Antonio")
                .lastName("Gomes")
                .weight(999)
                .build();
    }

    @Test
    public void testBuilder() {
        Person p = begin(PersonBuilder.class)
                .firstName("Antonio")
                .ssn("00000000")
                .active(true)
                .dob("1969-01-01")
                .address(begin(AddressBuilder.class)
                        .address1("285 Bay st")
                        .city("Long Beach")
                        .state("CA")
                        .country("USA"))
                .phone(begin(PhoneBuilder.class)
                        .number("00000")
                        .type("MOBILE"))
                .build();

        System.out.println("RESULT = " + p);

        assertThat(p.getFirstName()).isEqualTo("Antonio");
        assertThat(p.getActive()).isEqualTo(true);
        assertThat(p.getAddress()).isNotNull();
        assertThat(p.getAddress().getAddress1()).isEqualTo("285 Bay st");
        assertThat(p.getDob()).isEqualTo("1969-01-01");
    }

}
