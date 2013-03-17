package com.github.testr.sample.dal.fixture;

import com.github.testr.builder.IBuilder;
import com.github.testr.sample.dal.entity.Address;
import com.github.testr.sample.dal.entity.Book;
import com.github.testr.sample.dal.entity.User;

public interface BookBuilder extends IBuilder<Book> {
    BookBuilder name(String name);

    BookBuilder user(User user);
}
