package com.github.testr.builder;

public interface IObjectHandler {

    Object newInstance(BuilderInfo info);

    Object preProcess(Object o, BuilderInfo info);

    Object postProcess(Object o, BuilderInfo info);

}
