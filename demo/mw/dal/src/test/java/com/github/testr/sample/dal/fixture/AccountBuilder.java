package com.github.testr.sample.dal.fixture;

import com.github.testr.builder.IBuilder;
import com.github.testr.sample.dal.entity.Account;
import com.github.testr.sample.dal.entity.AccountType;
import com.github.testr.sample.dal.entity.Book;
import com.github.testr.sample.dal.entity.User;

public interface AccountBuilder extends IBuilder<Account> {
    AccountBuilder name(String name);

    AccountBuilder book(Book book);

    AccountBuilder initialBalance(double v);

    AccountBuilder type(AccountType v);
}
