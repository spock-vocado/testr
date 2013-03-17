package com.github.testr.demo.dal.fixture;

import com.github.testr.demo.dal.entity.EnumType;
import com.github.testr.demo.dal.entity.EnumValue;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class EnumTypeBuilder {

    private final EntityManager em;
    private EnumType et = new EnumType();
    private List<EnumValue> values = new ArrayList<>();

    public EnumTypeBuilder(EntityManager em) {
        this.em = em;
    }

    public EnumTypeBuilder name(String v) {
        et.setName(v);
        return this;
    }

    public EnumTypeBuilder value(String name, String value) {
        EnumValue ev = new EnumValue();
        ev.setName(name);
        ev.setValue(value);
        values.add(ev);
        return this;
    }

    public EnumTypeBuilder value(String name) {
        EnumValue ev = new EnumValue();
        ev.setName(name);
        ev.setValue(name);
        values.add(ev);
        return this;
    }

    public EnumType build() {
        em.persist(et);
        for (EnumValue ev : values) {
            ev.setEnumType(et);
            em.persist(ev);
        }
        em.flush();
        em.clear();
        return et;
    }

}
