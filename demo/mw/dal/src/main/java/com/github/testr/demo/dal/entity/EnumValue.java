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
@Table(name = "enum_value", uniqueConstraints = @UniqueConstraint(columnNames = {"enum_type_id", "name"}))
public class EnumValue extends AbstractBusinessEntity {

    private static final long serialVersionUID = -2952735933715107252L;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "enum_type_id", nullable = false)
    private EnumType enumType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public EnumType getEnumType() {
        return enumType;
    }

    public void setEnumType(EnumType enumType) {
        this.enumType = enumType;
    }
}