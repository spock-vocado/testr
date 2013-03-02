package com.github.testr.builder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultBuilderHandler implements IBuilderHandler {

    private static final Log log = LogFactory.getLog(DefaultBuilderHandler.class);

    @Override
    public Object newInstance(BuilderContext context) {
        log.debug("Creating new instance of " + context.getObjectClass().getName());
        return BuilderUtil.newInstance(context.getObjectClass());
    }

    @Override
    public Object preProcess(Object o, BuilderContext context) {
        return o;
    }

    @Override
    public Object postProcess(Object o, BuilderContext context) {
        return o;
    }

}
