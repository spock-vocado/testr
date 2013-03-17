package com.github.testr.demo.dal.fixture;

import com.github.testr.builder.IBuilder;
import com.github.testr.demo.dal.entity.Account;
import com.github.testr.demo.dal.entity.AccountType;
import com.github.testr.demo.dal.entity.Book;

public interface AccountBuilder extends IBuilder<Account> {
    AccountBuilder name(String name);

    AccountBuilder book(Book book);

    AccountBuilder initialBalance(double v);

    AccountBuilder type(AccountType v);
}
