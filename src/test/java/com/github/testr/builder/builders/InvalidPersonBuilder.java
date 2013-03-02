package com.github.testr.builder.builders;

import com.github.testr.builder.IBuilder;
import com.github.testr.builder.pojos.Address;
import com.github.testr.builder.pojos.Person;

public interface InvalidPersonBuilder extends IBuilder<Person> {

    InvalidPersonBuilder firstName(String firstName);

    InvalidPersonBuilder lastName(String lastName);

    // Hint: this field does not exist in Person
    InvalidPersonBuilder weight(double weight);

}