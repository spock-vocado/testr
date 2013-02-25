package com.github.testr.builder;

/**
 * Interface implemented by builders.
 *
 * @param <T> Type of the object to build
 */
public interface IBuilder<T> {

    /**
     * Builds object.
     *
     * @return object.
     */
    T build();

}
