package com.github.testbed.entity.builder;

import com.github.testbed.builder.IDynamicBuilder;
import com.github.testbed.entity.Address;
import com.github.testbed.entity.Person;

public interface PersonBuilder extends IDynamicBuilder<Person> {

    PersonBuilder firstName(String firstName);

    PersonBuilder lastName(String lastName);

    PersonBuilder dob(String isoDate);

    PersonBuilder ssn(String ssn);

    PersonBuilder address(Address address);

    PersonBuilder address(AddressBuilder addressBuilder);

}
