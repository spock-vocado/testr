package com.github.testr.sample.dal.repository;

import com.github.testr.sample.dal.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    @Query("select u from User u where u.firstName = ?1")
    List<User> findByFirstName(String firstname);

    List<User> findByLastName(String lastname);

}