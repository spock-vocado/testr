package com.github.testr.demo.service;

import com.github.testr.demo.dal.entity.EnumType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

@ContextConfiguration(locations = "classpath:test-context-service.xml")
public class EnumServiceTest extends AbstractServiceTest {

    @Autowired
    private EnumService enumService;

    @Test
    public void testFind() {
        EnumType et = enumService.newEnum("SECURITY_LEVEL", "LOW", "MEDIUM", "HIGH");
        assertThat(et).isNotNull();
        assertThat(et.getId()).isNotNull();
        assertThat(et.getValues()).isNotNull();
        assertThat(et.getValues()).hasSize(3);
    }

}