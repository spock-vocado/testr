package com.github.testr.sample.dal.fixture;

import com.github.testr.builder.BuilderFactory;
import com.github.testr.builder.jpa.JpaBuilderHandler;
import com.github.testr.sample.dal.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.github.testr.builder.BuilderHelper.begin;
import static com.github.testr.builder.BuilderHelper.setDefaultBuilderFactory;

public class DefaultFixture {

    public User user1;
    public User user2;

    @PersistenceContext
    private EntityManager em;

    public void run() {
        setDefaultBuilderFactory(new BuilderFactory(new JpaBuilderHandler(em)));

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
    }
}
