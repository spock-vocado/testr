package com.github.testr.builder;

public interface IBuilderHandler {

    Object newInstance(BuilderContext context);

    Object preProcess(Object o, BuilderContext context);

    Object postProcess(Object o, BuilderContext context);

}
