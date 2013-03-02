package com.github.testr.builder.builders;

import com.github.testr.builder.jpa.ChildOf;
import com.github.testr.builder.IBuilder;
import com.github.testr.builder.pojos.Person;
import com.github.testr.builder.pojos.Phone;

@ChildOf(entityClass = Person.class, parentProperty = "person")
public interface PhoneBuilder extends IBuilder<Phone> {

    PhoneBuilder type(String type);

    PhoneBuilder number(String number);

}
