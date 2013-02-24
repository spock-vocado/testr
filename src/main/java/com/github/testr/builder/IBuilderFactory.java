package com.github.testr.builder;

public interface IBuilderFactory {

    <T extends IBuilder<?>> T create(Class<T> builderClass);

}
