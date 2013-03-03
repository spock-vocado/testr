package com.github.testr.builder.jpa;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation added to a builder that marks its entity type as being child
 * of a given parent entity.
 */
@java.lang.annotation.Target({TYPE})
@java.lang.annotation.Retention(RUNTIME)
@java.lang.annotation.Documented
public @interface ChildOf {

    /**
     * Class of the parent entity.
     *
     * @return class
     */
    Class<?> entityClass();

    /**
     * Name of the property in the child that points to the parent.
     *
     * @return property name
     */
    String parentProperty();

}
