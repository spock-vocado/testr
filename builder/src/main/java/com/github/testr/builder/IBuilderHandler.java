package com.github.testr.builder;

public interface IBuilderHandler {

    void newInstance(BuilderContext context);

    void preProcess(BuilderContext context);

    void postProcess(BuilderContext context);

}
