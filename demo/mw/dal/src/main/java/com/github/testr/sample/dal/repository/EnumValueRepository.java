package com.github.testr.sample.dal.repository;

import com.github.testr.sample.dal.entity.EnumValue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EnumValueRepository extends CrudRepository<EnumValue, Long> {

}