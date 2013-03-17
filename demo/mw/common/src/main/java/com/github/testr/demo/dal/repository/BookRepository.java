package com.github.testr.demo.dal.repository;

import com.github.testr.demo.dal.entity.Book;
import com.github.testr.demo.dal.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BookRepository extends CrudRepository<Book, Long> {
    Book findByNameAndUser(String name, User u);
}