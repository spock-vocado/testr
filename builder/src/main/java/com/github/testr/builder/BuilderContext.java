package com.github.testr.builder;

public class BuilderContext {

    private final Class<? extends IBuilder<?>> builderClass;
    private final Class<?> objectClass;
    private final BuilderFactory builderFactory;

    public BuilderContext(Class<? extends IBuilder<?>> builderClass, Class<?> objectClass, BuilderFactory builderFactory) {
        this.builderClass = builderClass;
        this.objectClass = objectClass;
        this.builderFactory = builderFactory;
    }

    public Class<? extends IBuilder<?>> getBuilderClass() {
        return builderClass;
    }

    public Class<?> getObjectClass() {
        return objectClass;
    }

    public BuilderFactory getBuilderFactory() {
        return builderFactory;
    }
}
