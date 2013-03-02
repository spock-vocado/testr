package com.github.testr.builder;

import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class BuilderUtilTest {

    @Test
    public void testNewInstance() {
        assertThat(BuilderUtil.newInstance(Foo.class))
                .isNotNull()
                .isInstanceOf(Foo.class);
    }

    @Test
    public void testParseIsoDate() {
        assertThat(BuilderUtil.parseIsoDate("2013-01-01")).isEqualTo("2013-01-01");
    }

    public static class Foo {
        String bar;
    }

}
