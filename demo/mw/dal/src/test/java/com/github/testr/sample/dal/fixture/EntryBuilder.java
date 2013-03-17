package com.github.testr.sample.dal.fixture;

import com.github.testr.builder.IBuilder;
import com.github.testr.sample.dal.entity.Account;
import com.github.testr.sample.dal.entity.Book;
import com.github.testr.sample.dal.entity.Entry;
import com.github.testr.sample.dal.entity.EntryStatus;

public interface EntryBuilder extends IBuilder<Entry> {
    EntryBuilder amount(double v);

    EntryBuilder date(String isoDate);

    EntryBuilder status(EntryStatus s);

    EntryBuilder memo(String v);

    EntryBuilder from(Account acc);

    EntryBuilder to(Account acc);
}
