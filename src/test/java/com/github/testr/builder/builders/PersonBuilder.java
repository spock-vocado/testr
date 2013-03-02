package com.github.testr.builder.builders;

import com.github.testr.builder.IBuilder;
import com.github.testr.builder.pojos.Address;
import com.github.testr.builder.pojos.Person;

public interface PersonBuilder extends IBuilder<Person> {

    PersonBuilder firstName(String firstName);

    PersonBuilder lastName(String lastName);

    PersonBuilder dob(String isoDate);

    PersonBuilder ssn(String ssn);

    PersonBuilder address(Address address);

    PersonBuilder address(AddressBuilder addressBuilder);

    AddressBuilder address();

    PersonBuilder active(Boolean active);

    PersonBuilder phone(PhoneBuilder v);

    PhoneBuilder phone();
}