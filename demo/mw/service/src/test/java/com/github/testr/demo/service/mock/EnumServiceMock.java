package com.github.testr.demo.service.mock;

import com.github.testr.demo.dal.entity.EnumType;
import com.github.testr.demo.dal.entity.EnumValue;
import com.github.testr.demo.service.EnumService;

import java.util.ArrayList;

public class EnumServiceMock implements EnumService {

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
