package com.github.testr.builder;

/**
 * Interface that extends the behavior of {@link BuilderFactory}.
 */
public interface IBuilderHandler {

    /**
     * Creates a new object instance.
     * <p/>
     * The newly created object must be set via {@link BuilderContext#setValue(Object)}
     *
     * @param context The builder context
     */
    void newInstance(BuilderContext context);

    /**
     * Performs any required pre-processing.
     * <p/>
     * The current object can be accessed via {@link BuilderContext#getValue()}.
     *
     * @param context The builder context
     */
    void preProcess(BuilderContext context);

    /**
     * Performs any required post-processing.
     * <p/>
     * * The current object can be accessed via {@link BuilderContext#getValue()}.
     *
     * @param context The builder context
     */
    void postProcess(BuilderContext context);

}
