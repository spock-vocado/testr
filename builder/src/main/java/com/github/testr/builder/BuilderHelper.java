package com.github.testr.builder;

public abstract class BuilderHelper {

    private BuilderHelper() {
    }

    private static BuilderFactory factory;

    public static BuilderFactory getFactory() {
        return factory;
    }

    public static void setBuilderFactory(BuilderFactory factory) {
        BuilderHelper.factory = factory;
    }

    public static <T extends IBuilder<?>> T begin(Class<T> builderClass) {
        return factory.create(builderClass);
    }

}
