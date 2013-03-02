package com.github.testr.builder;


import java.util.ArrayList;
import java.util.List;

public class BuilderContext {

    private final Class<? extends IBuilder<?>> builderClass;
    private final Class<?> objectClass;
    private final BuilderFactory builderFactory;
    private final List<Runnable> postActions = new ArrayList<>();
    private Object value;

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


    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void addPostAction(Runnable r) {
        postActions.add(r);
    }

    public void flushPostActions() {
        for (Runnable r : postActions)
            r.run();
    }
}
