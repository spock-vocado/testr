package com.github.testr.demo.service;


import com.github.testr.demo.dal.entity.EnumType;

public interface EnumService {

    EnumType newEnum(String name, String... values);

}
