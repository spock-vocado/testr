package com.github.testr.demo.dal.util;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractBusinessEntity extends AbstractPersistable<Long> {

    private static final Map<String, EntityInfo> infoMap = new HashMap<>();

    protected AbstractBusinessEntity() {
        initEntityInfoIfNeeded(getClass());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(getClass().isInstance(o))) {
            return false;
        }
        EntityInfo ei = getEntityInfoOrFail(getClass());
        EqualsBuilder builder = new EqualsBuilder();
        try {
            for (PropertyInfo pi : ei.keys) {
                builder.append(pi.getter.invoke(this), pi.getter.invoke(o));
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Could not compute equals()", e);
        }
        return builder.isEquals();
    }

    @Override
    public int hashCode() {
        EntityInfo ei = getEntityInfoOrFail(getClass());
        HashCodeBuilder builder = new HashCodeBuilder();
        try {
            for (PropertyInfo pi : ei.keys) {
                builder.append(pi.getter.invoke(this));
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Could not compute hashCode()", e);
        }
        return builder.toHashCode();
    }

    @Override
    public String toString() {
        EntityInfo ei = getEntityInfoOrFail(getClass());
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
        try {
            builder.append(ei.pk.friendlyName(), ei.pk.getter.invoke(this));
            for (PropertyInfo pi : ei.keys) {
                if (pi.name.equals("id")) continue;
                builder.append(pi.friendlyName(), pi.getter.invoke(this));
            }
            for (PropertyInfo pi : ei.addToString) {
                if (pi.name.equals("id")) continue;
                builder.append(pi.friendlyName(), pi.getter.invoke(this));
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Could not compute toString()", e);
        }
        return builder.toString();
    }

    private static synchronized void initEntityInfoIfNeeded(Class<?> clazz) {
        if (infoMap.containsKey(clazz.getName())) {
            return;
        }
        BusinessEntity ann = clazz.getAnnotation(BusinessEntity.class);
        String[] keys;
        String[] addToString;
        if (ann != null) {
            keys = ann.keys();
            addToString = ann.addToString();
        } else {
            keys = new String[]{"id"};
            addToString = new String[0];
        }
        if (keys.length == 0) {
            throw new IllegalArgumentException("@BusinessKey requires at least one property name in " + clazz);
        }
        Map<String, PropertyInfo> props = new HashMap<>();
        EntityInfo info = new EntityInfo();
        for (String prop : keys) {
            if (props.containsKey(prop)) {
                throw new IllegalArgumentException("Property specified multiple times in " + clazz);
            }
            PropertyInfo pi = new PropertyInfo(findGetterOrFail(clazz, prop), prop, true);
            props.put(prop, pi);
            info.keys.add(pi);
        }
        for (String prop : addToString) {
            if (props.containsKey(prop)) {
                throw new IllegalArgumentException("Property '" + prop + "' is specified multiple times in " + clazz);
            }
            PropertyInfo pi = new PropertyInfo(findGetterOrFail(clazz, prop), prop, false);
            props.put(prop, pi);
            info.keys.add(pi);
        }
        info.pk = props.get("id");
        if (info.pk == null) {
            info.pk = new PropertyInfo(findGetterOrFail(clazz, "id"), "id", false);
        }
        infoMap.put(clazz.getName(), info);
    }

    private static Method findGetterOrFail(Class<?> clazz, String k) {
        final String suffix = Character.toUpperCase(k.charAt(0)) + k.substring(1);
        Method m = ReflectionUtils.findMethod(clazz, "get" + suffix);
        if (m == null) {
            m = ReflectionUtils.findMethod(clazz, "is" + suffix);
        }
        if (m == null) {
            throw new IllegalArgumentException("Could not find getter for property '" + k + "' in " + clazz);
        }
        return m;
    }

    private static synchronized EntityInfo getEntityInfoOrFail(Class<?> clazz) {
        EntityInfo info = infoMap.get(clazz.getName());
        if (info == null) {
            throw new IllegalArgumentException("Entity information for '" + clazz.getName() + " not available");
        }
        return info;
    }

    private static class PropertyInfo {
        final Method getter;
        final String name;
        final boolean isBusinessKey;

        private PropertyInfo(Method getter, String name, boolean businessKey) {
            this.getter = getter;
            this.name = name;
            this.isBusinessKey = businessKey;
        }

        public String friendlyName() {
            return isBusinessKey ? ("*" + name) : name;
        }

    }

    private static class EntityInfo {
        PropertyInfo pk;
        final List<PropertyInfo> keys = new ArrayList<>();
        final List<PropertyInfo> addToString = new ArrayList<>();
    }

}
