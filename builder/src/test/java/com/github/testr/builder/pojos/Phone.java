package com.github.testr.builder.pojos;

import com.github.testr.builder.jpa.ChildOf;

@ChildOf(entityClass = Person.class, parentProperty = "foo")
public class Phone {

    private String number;
    private String type;
    private Person person;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Phone");
        sb.append("{code='").append(number).append('\'');
        sb.append(", description='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
