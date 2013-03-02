package com.github.testr.sample.fixture;

import com.github.testr.builder.IBuilder;
import com.github.testr.builder.jpa.ChildOf;
import com.github.testr.sample.model.Address;
import com.github.testr.sample.model.User;

@ChildOf(entityClass = User.class, parentProperty = "user")
public interface AddressBuilder extends IBuilder<Address> {
    AddressBuilder address1(String v);

    AddressBuilder address2(String v);

    AddressBuilder city(String v);

    AddressBuilder state(String v);

    AddressBuilder country(String v);
}
