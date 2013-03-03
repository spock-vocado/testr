package com.github.testr.builder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Default implementation of the interface {@link IBuilderHandler}
 * that performs no additional processing.
 */
public class DefaultBuilderHandler implements IBuilderHandler {

    private static final Log log = LogFactory.getLog(DefaultBuilderHandler.class);

    @Override
    public void newInstance(BuilderContext context) {
        log.debug("Creating new instance of " + context.getObjectClass().getName());
        context.setValue(BuilderUtil.newInstance(context.getObjectClass()));
    }

    @Override
    public void preProcess(BuilderContext context) {
    }

    @Override
    public void postProcess(BuilderContext context) {
    }

}
