package com.github.testr.demo.dal.repository;

import com.github.testr.demo.dal.entity.EnumType;
import com.github.testr.demo.dal.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface EnumTypeRepository extends CrudRepository<EnumType, Long>, EnumTypeRepositoryCustom {

    List<User> findByName(String name);

}