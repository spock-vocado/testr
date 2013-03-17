package com.github.testr.demo.service.impl;

import com.github.testr.demo.dal.entity.EnumType;
import com.github.testr.demo.dal.entity.EnumValue;
import com.github.testr.demo.dal.repository.EnumTypeRepository;
import com.github.testr.demo.dal.repository.EnumValueRepository;
import com.github.testr.demo.service.EnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("enumService")
public class EnumServiceImpl implements EnumService {

    @Autowired
    private EnumTypeRepository enumTypeRepository;

    @Autowired
    private EnumValueRepository enumValueRepository;

    public EnumType newEnum(String name, String... values) {
        EnumType et = new EnumType();
        et.setName(name);
        enumTypeRepository.save(et);
        for (String v : values) {
            EnumValue ev = new EnumValue();
            ev.setName(v);
            ev.setEnumType(et);
            enumValueRepository.save(ev);
        }
        enumTypeRepository.refresh(et);
        return et;
    }

}
