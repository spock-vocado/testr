package com.github.testr.sample.fixture;

import com.github.testr.builder.IBuilder;
import com.github.testr.sample.model.Address;
import com.github.testr.sample.model.EnumType;
import com.github.testr.sample.model.User;

public interface EnumTypeBuilder extends IBuilder<EnumType> {
    EnumTypeBuilder name(String v);
}
