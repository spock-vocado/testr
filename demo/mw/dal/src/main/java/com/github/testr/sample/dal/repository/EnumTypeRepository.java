package com.github.testr.sample.dal.repository;

import com.github.testr.sample.dal.entity.EnumType;
import com.github.testr.sample.dal.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface EnumTypeRepository extends CrudRepository<EnumType, Long>, EnumTypeRepositoryCustom {

    List<User> findByName(String name);

}