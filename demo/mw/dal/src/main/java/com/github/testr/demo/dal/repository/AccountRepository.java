package com.github.testr.demo.dal.repository;

import com.github.testr.demo.dal.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AccountRepository extends CrudRepository<Account, Long> {
}