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
import java.util.concurrent.ConcurrentHashMap;

/**
 * Abstract class for JPA entities that automatically support equals/hashCode() and toString()
 * operations based on a list of business keys declared via @{@link BusinessEntity}.
 * <p/>
 * todo: ensure subclasses declare all business key of parents
 */
@SuppressWarnings("unchecked")
public abstract class AbstractBusinessEntity extends AbstractPersistable<Long> {

    private static final Map<String, EntityInfo> entityInfoMap = new ConcurrentHashMap<>();
    private static final String PK_NAME = "id";

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(getClass().isInstance(o))) {
            return false;
        }
        EntityInfo thisInfo = getEntityInfo(getClass());
        EntityInfo otherInfo = getEntityInfo((Class<? extends AbstractBusinessEntity>) o.getClass());
        if (!thisInfo.keysMatch(otherInfo)) {
            return false;
        }
        EqualsBuilder builder = new EqualsBuilder();
        try {
            for (PropertyInfo pi : thisInfo.keys) {
                builder.append(pi.getter.invoke(this), pi.getter.invoke(o));
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Could not compute equals()", e);
        }
        return builder.isEquals();
    }

    @Override
    public int hashCode() {
        EntityInfo ei = getEntityInfo(getClass());
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
        EntityInfo ei = getEntityInfo(getClass());
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
        try {
            builder.append(ei.pk.friendlyName(), ei.pk.getter.invoke(this));
            for (PropertyInfo pi : ei.keys) {
                if (pi.name.equals(PK_NAME)) continue;
                builder.append(pi.friendlyName(), pi.getter.invoke(this));
            }
            for (PropertyInfo pi : ei.addToString) {
                if (pi.name.equals(PK_NAME)) continue;
                builder.append(pi.friendlyName(), pi.getter.invoke(this));
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Could not compute toString()", e);
        }
        return builder.toString();
    }

    /**
     * Gets the information about an entity.
     * <p/>
     * Notes:
     * <ul>
     * <li>This method is thread-safe</li>
     * <li>Entity information is constructed lazily and cached</li>
     * <li>Because of the on-demand initialization, java serialization support is transparent</li>
     * </ul>
     *
     * @param entityClass The entity class
     * @return entity information (never <code>null</code>)
     * @throws IllegalArgumentException if the entity information cannot be detected
     */
    private static EntityInfo getEntityInfo(Class<? extends AbstractBusinessEntity> entityClass) {
        EntityInfo info = entityInfoMap.get(entityClass.getName());
        if (info == null) {
            return safeInitEntityInfo(entityClass);
        }
        return info;
    }

    /**
     * Initializes the information about an entity class.
     * <p/>
     * This method is thread-safe and idempotent.
     *
     * @param clazz The entity class
     * @return entity information (will never be <code>null</code>)
     * @throws IllegalArgumentException if the entity information cannot be constructed
     */
    private static synchronized EntityInfo safeInitEntityInfo(Class<? extends AbstractBusinessEntity> clazz) {
        //
        // [Step 1] Double check just in case something changed right before
        // entering the synchronized block. This should happen only if multiple
        // threads access the same entity for the same time, so it's very very rare
        //
        EntityInfo info = entityInfoMap.get(clazz.getName());
        if (info != null) {
            return info;
        }
        //
        // [Step 2] Continue and initialize the entity information accordingly
        //

        // [Step 2.1] Detect parameters (assume default keys = {"id"})
        BusinessEntity ann = clazz.getAnnotation(BusinessEntity.class);
        String[] keyNames;
        String[] addToStringNames;
        if (ann != null) {
            keyNames = ann.keys();
            addToStringNames = ann.addToString();
        } else {
            keyNames = new String[]{PK_NAME};
            addToStringNames = new String[0];
        }
        if (keyNames.length == 0) {
            throw new IllegalArgumentException("@BusinessKey requires at least one property name in " + clazz);
        }

        // [Step 2.2] Initialize business keyPropList...
        Map<String, PropertyInfo> props = new HashMap<>();
        List<PropertyInfo> keyPropList = new ArrayList<>();
        List<PropertyInfo> addToStringPropList = new ArrayList<>();
        PropertyInfo pkProp;
        for (String prop : keyNames) {
            if (props.containsKey(prop)) {
                throw new IllegalArgumentException("Property specified multiple times in " + clazz);
            }
            PropertyInfo pi = new PropertyInfo(findGetterOrFail(clazz, prop), prop, true);
            props.put(prop, pi);
            keyPropList.add(pi);
        }

        // [Step 2.3] Initialize additional toString properties...
        for (String prop : addToStringNames) {
            if (props.containsKey(prop)) {
                throw new IllegalArgumentException("Property '" + prop + "' is specified multiple times in " + clazz);
            }
            PropertyInfo pi = new PropertyInfo(findGetterOrFail(clazz, prop), prop, false);
            props.put(prop, pi);
            addToStringPropList.add(pi);
        }

        // [Step 2.4] Ensure the PK is also detected
        pkProp = props.get(PK_NAME);
        if (pkProp == null) {
            pkProp = new PropertyInfo(findGetterOrFail(clazz, PK_NAME), PK_NAME, false);
        }

        info = new EntityInfo(pkProp, keyPropList, addToStringPropList);
        entityInfoMap.put(clazz.getName(), info);
        return info;
    }

    private static Method findGetterOrFail(Class<?> clazz, String isBusinessKey) {
        final String suffix = Character.toUpperCase(isBusinessKey.charAt(0)) + isBusinessKey.substring(1);
        Method m = ReflectionUtils.findMethod(clazz, "get" + suffix);
        if (m == null) {
            m = ReflectionUtils.findMethod(clazz, "is" + suffix);
        }
        if (m == null) {
            throw new IllegalArgumentException("Could not find getter for property '"
                    + isBusinessKey + "' in " + clazz);
        }
        return m;
    }

    /**
     * Immutable class that contains information about an entity field.
     */
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

    /**
     * Immutable class that contains information about an entity.
     */
    private static class EntityInfo {
        final PropertyInfo pk;
        final PropertyInfo[] keys;
        final PropertyInfo[] addToString;

        private EntityInfo(PropertyInfo pk, List<PropertyInfo> keys, List<PropertyInfo> addToString) {
            this.pk = pk;
            this.keys = keys.toArray(new PropertyInfo[keys.size()]);
            this.addToString = addToString.toArray(new PropertyInfo[addToString.size()]);
        }

        public boolean keysMatch(EntityInfo other) {
            if (keys.length != other.keys.length) {
                return false;
            }
            for (int i = 0; i < keys.length; i++) {
                if (!keys[i].name.equals(other.keys[i].name)) {
                    return false;
                }
            }
            return true;
        }
    }

}
