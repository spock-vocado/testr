package com.github.testr.builder;

public interface IBuilderFactory {

    <T extends IBuilder<?>> T get(Class<T> builderClass);

}
