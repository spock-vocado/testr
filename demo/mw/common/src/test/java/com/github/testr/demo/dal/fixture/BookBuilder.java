package com.github.testr.demo.dal.fixture;

import com.github.testr.builder.IBuilder;
import com.github.testr.demo.dal.entity.Book;
import com.github.testr.demo.dal.entity.User;

public interface BookBuilder extends IBuilder<Book> {
    BookBuilder name(String name);

    BookBuilder user(User user);
}
