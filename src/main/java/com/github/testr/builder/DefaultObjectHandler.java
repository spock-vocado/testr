package com.github.testr.builder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultObjectHandler implements IObjectHandler {

    private static final Log log = LogFactory.getLog(DefaultObjectHandler.class);

    @Override
    public Object newInstance(BuilderInfo info) {
        Object v = BuilderUtil.newInstance(info.getObjectClass());
        log.debug("NewInstance: " + info.getObjectClass().getName());
        return v;
    }

    @Override
    public Object preProcess(Object o, BuilderInfo info) {
        return o;
    }

    @Override
    public Object postProcess(Object o, BuilderInfo info) {
        return o;
    }

}
