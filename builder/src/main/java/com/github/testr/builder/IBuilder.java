package com.github.testr.builder;

/**
 * Interface implemented by builders.
 * <p/>
 * This interface is implemented dynamically by {@link BuilderFactory#create(Class)}.
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
