package com.github.testr.demo.dal.repository;

import com.github.testr.demo.dal.entity.EnumValue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EnumValueRepository extends CrudRepository<EnumValue, Long> {

}