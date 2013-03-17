package com.github.testr.demo.dal.fixture;

import com.github.testr.builder.BuilderFactory;
import com.github.testr.builder.jpa.JpaBuilderHandler;
import com.github.testr.demo.dal.entity.Account;
import com.github.testr.demo.dal.entity.AccountType;
import com.github.testr.demo.dal.entity.Book;
import com.github.testr.demo.dal.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.github.testr.builder.BuilderHelper.begin;
import static com.github.testr.builder.BuilderHelper.setDefaultBuilderFactory;

public class DefaultFixture {

    public User user1;
    public Book book1;
    public Account account1_1;
    public Account account1_2;
    public Account account1_3;

    public User user2;
    public Book book2;
    public Account account2_1;

    @PersistenceContext
    private EntityManager em;

    public void run() {
        setDefaultBuilderFactory(new BuilderFactory(new JpaBuilderHandler(em)));
        initUser1();
        initUser2();
    }

    private void initUser1() {
        user1 = begin(UserBuilder.class)
                .username("user")
                .firstName("FirstName")
                .lastName("LastName")
                .email("user@domain.com")
                .address(begin(AddressBuilder.class)
                        .address1("666 Main St.")
                        .city("Long Beach")
                        .state("CA")
                        .country("USA"))
                .build();
        book1 = begin(BookBuilder.class)
                .name("Antonio")
                .user(user1)
                .build();
        account1_1 = begin(AccountBuilder.class)
                .name("ITAU")
                .book(book1)
                .type(AccountType.BANK)
                .initialBalance(0d)
                .build();
        account1_2 = begin(AccountBuilder.class)
                .name("CAIXA")
                .book(book1)
                .type(AccountType.BANK)
                .initialBalance(0d)
                .build();
        account1_3 = begin(AccountBuilder.class)
                .name("CASH")
                .book(book1)
                .type(AccountType.CASH)
                .initialBalance(0d)
                .build();
    }

    private void initUser2() {
        user2 = begin(UserBuilder.class)
                .username("user2")
                .firstName("FirstName2")
                .lastName("LastName2")
                .email("user2@domain.com")
                .address(begin(AddressBuilder.class)
                        .address1("999 Main St.")
                        .city("Long Beach")
                        .state("CA")
                        .country("USA"))
                .build();
        book2 = begin(BookBuilder.class)
                .name("John")
                .user(user1)
                .build();
        account2_1 = begin(AccountBuilder.class)
                .name("CHASE")
                .book(book1)
                .type(AccountType.BANK)
                .initialBalance(0d)
                .build();
    }
}
