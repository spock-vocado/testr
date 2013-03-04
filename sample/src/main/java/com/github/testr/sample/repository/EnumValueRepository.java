package com.github.testr.sample.repository;

import com.github.testr.sample.model.EnumType;
import com.github.testr.sample.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface EnumValueRepository extends CrudRepository<EnumType, Long> {

    @Query("select ev from EnumValue ev where ev.enumType.name = ?1 and ev.name = ?2")
    List<User> findByTypeNameAndName(String typeName, String name);

    @Query("select ev from EnumValue ev where ev.enumType = ?1 and ev.name = ?2")
    List<User> findByTypeAndName(EnumType type, String name);

}