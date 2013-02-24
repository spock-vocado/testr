package com.github.testr.builder.extra;

import com.github.testr.builder.IBuilder;

public interface AddressBuilder extends IBuilder<Address> {

    AddressBuilder address1(String address1);

    AddressBuilder address2(String address2);

    AddressBuilder city(String city);

    AddressBuilder state(String state);

    AddressBuilder country(String country);

    AddressBuilder postalCode(String postalCode);

}
