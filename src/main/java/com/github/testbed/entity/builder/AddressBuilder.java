package com.github.testbed.entity.builder;

import com.github.testbed.builder.IDynamicBuilder;
import com.github.testbed.entity.Address;

public interface AddressBuilder extends IDynamicBuilder<Address> {

    AddressBuilder address1(String address1);

    AddressBuilder address2(String address2);

    AddressBuilder city(String city);

    AddressBuilder state(String state);

    AddressBuilder country(String country);

    AddressBuilder postalCode(String postalCode);

}
