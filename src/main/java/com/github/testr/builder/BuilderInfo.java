package com.github.testr.builder;

public class BuilderInfo {

    private final Class<? extends IBuilder<?>> builderClass;
    private final Class<?> objectClass;

    public BuilderInfo(Class<? extends IBuilder<?>> builderClass, Class<?> objectClass) {
        this.builderClass = builderClass;
        this.objectClass = objectClass;
    }

    public Class<? extends IBuilder<?>> getBuilderClass() {
        return builderClass;
    }

    public Class<?> getObjectClass() {
        return objectClass;
    }
}
