package com.github.testr.sample.repository;

import com.github.testr.sample.model.EnumType;
import com.github.testr.sample.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface EnumTypeRepository extends CrudRepository<EnumType, Long> {

    List<User> findByName(String firstname);

}