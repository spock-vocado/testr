package com.github.testr.demo.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Env {

    public static final String PROP_KEY = "config.location";

    public Resource getResource(String resourceName) {
        String location = System.getProperty(PROP_KEY);
        if (StringUtils.isBlank(location)) {
            return new ClassPathResource(resourceName);
        }
        FileSystemResource resource = new FileSystemResource(location + "/" + resourceName);
        return resource.exists() ? resource : null;
    }

}
