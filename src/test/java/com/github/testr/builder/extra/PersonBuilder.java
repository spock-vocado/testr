package com.github.testr.builder.extra;

import com.github.testr.builder.IBuilder;

public interface PersonBuilder extends IBuilder<Person> {

    PersonBuilder firstName(String firstName);

    PersonBuilder lastName(String lastName);

    PersonBuilder dob(String isoDate);

    PersonBuilder ssn(String ssn);

    PersonBuilder address(Address address);

    PersonBuilder address(AddressBuilder addressBuilder);

    PersonBuilder active(Boolean active);
}