package com.github.testbed.builder;

public interface IHandler {

    Object newInstance(Class<?> objectClass);

    Object postProcess(Object v);

}
