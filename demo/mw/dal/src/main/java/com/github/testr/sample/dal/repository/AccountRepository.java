package com.github.testr.sample.dal.repository;

import com.github.testr.sample.dal.entity.Account;
import com.github.testr.sample.dal.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AccountRepository extends CrudRepository<Account, Long> {
}