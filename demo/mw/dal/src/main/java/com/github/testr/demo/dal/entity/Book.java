package com.github.testr.demo.dal.entity;

import com.github.testr.demo.dal.util.AbstractBusinessEntity;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "book", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "user_id"})
})
public class Book extends AbstractBusinessEntity {

    private static final long serialVersionUID = -2952735933715107252L;

    @Column(name="name", nullable = false, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}