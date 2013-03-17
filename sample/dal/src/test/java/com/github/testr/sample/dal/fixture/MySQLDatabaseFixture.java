package com.github.testr.sample.dal.fixture;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MySQLDatabaseFixture {

    private static final Log log = LogFactory.getLog(MySQLDatabaseFixture.class);

    private static final Pattern urlRegex = Pattern.compile("^(jdbc:mysql://[^/]+)/([^/]+)(/.+)?$");

    private String driver = "com.mysql.jdbc.Driver";
    private String username = "root";
    private String password = "";
    private String url;
    private Resource[] sqlScripts;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Resource[] getSqlScripts() {
        return sqlScripts;
    }

    public void setSqlScripts(Resource[] sqlScripts) {
        this.sqlScripts = sqlScripts;
    }

    @PostConstruct
    public void init() {
        log.info("Starting DataSource pre-initiation");
        Assert.notNull(driver, "'driver' property is required");
        Assert.notNull(username, "'username' property is required");
        Assert.notNull(password, "'password' property is required");
        Assert.notNull(url, "'url' property is required");

        String bootstrapUrl;
        String database;
        Matcher m = urlRegex.matcher(url);
        if (m.matches()) {
            bootstrapUrl = m.group(1);
            database = m.group(2);
        } else {
            throw new IllegalArgumentException("Unsupported jdbc url: must match " + urlRegex);
        }

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(bootstrapUrl);
        ds.setUsername(username);
        ds.setPassword(password);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

        jdbcTemplate.execute("DROP DATABASE IF EXISTS " + database);
        jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS " + database);

        // reconfigure datasource to the final URL and execute any required script
        if (sqlScripts != null) {
            ds.setUrl(url);
            for (Resource r : sqlScripts)
                JdbcTestUtils.executeSqlScript(jdbcTemplate, new EncodedResource(r, "utf-8"), false);
        }

        log.info("Finished DataSource pre-initiation");
    }

}
