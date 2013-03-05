package com.github.testr.sample.service.impl;

import com.github.testr.sample.dal.entity.EnumType;
import com.github.testr.sample.dal.entity.EnumValue;
import com.github.testr.sample.dal.repository.EnumTypeRepository;
import com.github.testr.sample.dal.repository.EnumValueRepository;
import com.github.testr.sample.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("otherService")
public class OtherServiceImpl implements OtherService {

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
