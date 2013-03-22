package com.github.testr.demo.dal.entity;

import com.github.testr.demo.dal.util.AbstractBusinessEntity;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.persistence.EnumType;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "entry")
public class Entry extends AbstractBusinessEntity {

    private static final long serialVersionUID = -2952735933715107252L;

    @Column(length = 50)
    private String memo;

    @Column(nullable = false, columnDefinition = "DECIMAL")
    private BigDecimal amount;

    @Temporal(TemporalType.DATE)
    @Column(name = "posting_date", nullable = false)
    private Date postingDate;

    @ManyToOne
    @JoinColumn(name = "from_account_id", nullable = false)
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account_id", nullable = false)
    private Account toAccount;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "CHAR(1)")
    private EntryStatus status;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public EntryStatus getStatus() {
        return status;
    }

    public void setStatus(EntryStatus status) {
        this.status = status;
    }

}