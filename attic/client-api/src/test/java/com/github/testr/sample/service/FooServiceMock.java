package com.github.testr.demo.service;

import com.github.testr.demo.dal.entity.EnumType;
import com.github.testr.demo.dal.entity.EnumValue;

import java.util.ArrayList;

public class FooServiceMock implements OtherService {

    @Override
    public EnumType newEnum(String name, String... values) {
        System.out.println("MOCK: Creating enum '" + name + "'");
        EnumType et = new EnumType();
        et.setName("name");
        et.setValues(new ArrayList<EnumValue>());
        for (String v : values) {
            EnumValue ev = new EnumValue();
            ev.setName(v);
            ev.setEnumType(et);
            et.getValues().add(ev);
        }
        return et;
    }

}
