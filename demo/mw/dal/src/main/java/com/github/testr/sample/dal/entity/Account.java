package com.github.testr.sample.dal.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.persistence.EnumType;
import java.math.BigDecimal;

@Entity
@Table(name = "account", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "book_id"})
})
public class Account extends AbstractPersistable<Long> {

    private static final long serialVersionUID = -2952735933715107252L;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "initial_balance")
    private BigDecimal initialBalance;

    @Enumerated(EnumType.STRING)
    @Column(name="type", nullable = false)
    private AccountType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Account");
        sb.append("{name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}