package com.github.testr.sample.service;


import com.github.testr.sample.dal.entity.EnumType;

public interface OtherService {

    EnumType newEnum(String name, String... values);

}
