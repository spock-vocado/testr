package com.github.testr.demo.dal.fixture;

import com.github.testr.builder.IBuilder;
import com.github.testr.demo.dal.entity.Address;
import com.github.testr.demo.dal.entity.User;

public interface UserBuilder extends IBuilder<User> {
    UserBuilder username(String v);

    UserBuilder firstName(String v);

    UserBuilder lastName(String v);

    UserBuilder email(String v);

    UserBuilder address(Address v);

    UserBuilder address(AddressBuilder v);
}
