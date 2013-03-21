package com.github.testr.demo.dal.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.DynamicParameterizedType;
import org.hibernate.usertype.UserType;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

// see http://www.gabiaxel.com/2011/01/better-enum-mapping-with-hibernate.html
// see http://javadata.blogspot.de/2011/07/hibernate-and-enum-handling.html
public class CustomKeyEnumUserType implements UserType, DynamicParameterizedType {

    private Class<? extends Enum> enumClass;
    private Method getter;

    @Override
    public Object assemble(Serializable cached, Object owner)
            throws HibernateException {
        return cached;
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x == y;
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x == null ? 0 : x.hashCode();
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
            throws HibernateException, SQLException {
        int id = rs.getInt(names[0]);
        if (rs.wasNull())
            return null;
        for (Enum<?> value : enumClass.getEnumConstants()) {
            Integer _id = (Integer) ReflectionUtils.invokeMethod(getter, value);
            if (id == _id)
                return value;
        }
        throw new IllegalStateException("Unknown " + returnedClass().getName() + " id");
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
            throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, Types.INTEGER);
        } else {
            Integer _id = (Integer) ReflectionUtils.invokeMethod(getter, value);
            st.setInt(index, _id);
        }
    }

    @Override
    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        return original;
    }

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.INTEGER};
    }

    @Override
    public Class returnedClass() {
        return enumClass;
    }

    @Override
    public void setParameterValues(Properties parameters) {
        DynamicParameterizedType.ParameterType reader =
                (DynamicParameterizedType.ParameterType) parameters.get(PARAMETER_TYPE);
        if (reader == null) {
            throw new HibernateException("Could not set parameters");
        }
        String getterName = parameters.getProperty("getter", "getKey");
        enumClass = reader.getReturnedClass().asSubclass(Enum.class);
        getter = ReflectionUtils.findMethod(enumClass, getterName);
        if (getter == null || (getter.getReturnType() != Integer.class && getter.getReturnType() != int.class)) {
            throw new HibernateException("Could not find method 'int " + getterName + "()' in " + enumClass);
        }
    }

}