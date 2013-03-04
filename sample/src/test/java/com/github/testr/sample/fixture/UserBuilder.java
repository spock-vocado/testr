package com.github.testr.sample.fixture;

import com.github.testr.builder.IBuilder;
import com.github.testr.sample.model.testr.Address;
import com.github.testr.sample.model.testr.User;

public interface UserBuilder extends IBuilder<User> {
    UserBuilder username(String v);

    UserBuilder firstName(String v);

    UserBuilder lastName(String v);

    UserBuilder email(String v);

    UserBuilder address(Address v);

    UserBuilder address(AddressBuilder v);
}
