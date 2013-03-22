package com.github.testr.demo.dal.entity;

import com.github.testr.demo.dal.util.AbstractBusinessEntity;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "enum_type")
public class EnumType extends AbstractBusinessEntity {

    private static final long serialVersionUID = -2952735933715107252L;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "enumType")
    private List<EnumValue> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EnumValue> getValues() {
        return values;
    }

    public void setValues(List<EnumValue> values) {
        this.values = values;
    }

}