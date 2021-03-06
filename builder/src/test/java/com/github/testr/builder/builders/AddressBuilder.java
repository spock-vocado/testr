package com.github.testr.builder.builders;

import com.github.testr.builder.jpa.ChildOf;
import com.github.testr.builder.IBuilder;
import com.github.testr.builder.pojos.Address;
import com.github.testr.builder.pojos.Person;

@ChildOf(entityClass = Person.class, parentProperty = "person")
public interface AddressBuilder extends IBuilder<Address> {

    AddressBuilder address1(String address1);

    AddressBuilder address2(String address2);

    AddressBuilder city(String city);

    AddressBuilder state(String state);

    AddressBuilder country(String country);

    AddressBuilder postalCode(String postalCode);

}
