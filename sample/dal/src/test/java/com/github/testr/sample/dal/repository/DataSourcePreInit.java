package com.github.testr.sample.dal.repository;

import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSourcePreInit {

    private String driver = "com.mysql.jdbc.Driver";
    private String initialUrl = "jdbc:mysql://localhost";
    private String username = "root";
    private String password = "";
    private String database = "testr";

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getInitialUrl() {
        return initialUrl;
    }

    public void setInitialUrl(String initialUrl) {
        this.initialUrl = initialUrl;
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

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    @PostConstruct
    public void init() {
        Assert.notNull(driver, "'driver' property is required");
        Assert.notNull(initialUrl, "'initialUrl' property is required");
        Assert.notNull(username, "'username' property is required");
        Assert.notNull(password, "'password' property is required");
        Assert.notNull(database, "'database' property is required");

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(initialUrl, username, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + database);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
