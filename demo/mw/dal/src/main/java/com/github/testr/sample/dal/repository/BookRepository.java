package com.github.testr.sample.dal.repository;

import com.github.testr.sample.dal.entity.Book;
import com.github.testr.sample.dal.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BookRepository extends CrudRepository<Book, Long> {
    Book findByNameAndUser(String name, User u);
}