package com.github.testr.demo.dal.fixture;

import com.github.testr.builder.IBuilder;
import com.github.testr.demo.dal.entity.Account;
import com.github.testr.demo.dal.entity.Entry;
import com.github.testr.demo.dal.entity.EntryStatus;

public interface EntryBuilder extends IBuilder<Entry> {
    EntryBuilder amount(double v);

    EntryBuilder date(String isoDate);

    EntryBuilder status(EntryStatus s);

    EntryBuilder memo(String v);

    EntryBuilder from(Account acc);

    EntryBuilder to(Account acc);
}
