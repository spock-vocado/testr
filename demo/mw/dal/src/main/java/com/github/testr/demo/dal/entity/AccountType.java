package com.github.testr.demo.dal.entity;

public enum AccountType {

    BANK(0),
    CREDIT_CARD(1),
    CASH(2),
    PAYABLES(3),
    RECEIVABLES(4);

    private final int key;

    private AccountType(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

}
