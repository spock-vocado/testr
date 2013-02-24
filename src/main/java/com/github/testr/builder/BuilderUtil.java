package com.github.testr.builder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("unchecked")
abstract class BuilderUtil {

    private static final SimpleDateFormat isoDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private BuilderUtil() {
    }

    public static <T> T newInstance(Class<?> clazz) {
        try {
            return (T) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Date parseIsoDate(String isoDate) {
        try {
            return isoDateFormat.parse(isoDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
