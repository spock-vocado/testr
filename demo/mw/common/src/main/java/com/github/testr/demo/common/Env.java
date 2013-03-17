/**
 * COPYRIGHT (C) 2012 VOCADO, LLC.  ALL RIGHTS RESERVED.  THIS SOFTWARE CONTAINS
 * TRADE SECRETS AND/OR CONFIDENTIAL INFORMATION PROPRIETARY TO VOCADO, LLC
 * AND/OR ITS LICENSORS. ACCESS TO AND USE OF THIS INFORMATION IS STRICTLY
 * LIMITED AND CONTROLLED BY VOCADO, LLC.  THIS SOFTWARE MAY NOT BE COPIED,
 * MODIFIED, DISTRIBUTED, DISPLAYED, DISCLOSED OR USED IN ANY WAY NOT EXPRESSLY
 * AUTHORIZED BY VOCADO, LLC IN WRITING.
 */
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
        return new FileSystemResource(location + "/" + resourceName);
    }

}
