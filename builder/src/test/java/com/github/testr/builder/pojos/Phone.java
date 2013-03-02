package com.github.testr.builder.pojos;

import com.github.testr.builder.jpa.ChildOf;

@ChildOf(Person.class)
public class Phone {

    private String number;
    private String type;

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
