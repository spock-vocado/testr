package com.github.testr.builder.annotation;

import com.github.testr.builder.IBuilder;

public interface IBuilderFactory {

    <T extends IBuilder<?>> T create(Class<T> builderClass);

}
