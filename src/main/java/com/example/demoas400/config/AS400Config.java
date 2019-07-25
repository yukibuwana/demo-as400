package com.example.demoas400.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@EnableConfigurationProperties(AS400ConfigProperties.class)
public class AS400Config {
    private final AS400ConfigProperties as400ConfigProperties;

    public AS400Config(AS400ConfigProperties as400ConfigProperties) {
        this.as400ConfigProperties = as400ConfigProperties;
    }

    public List<String> connect() {
        List<String> result = new ArrayList<>();

        try {
            log.info("Trying to connect...");
            Class.forName(as400ConfigProperties.getDriver());
            Connection conn = DriverManager.getConnection(as400ConfigProperties.getUrl(),
                    as400ConfigProperties.getUsername(), as400ConfigProperties.getPassword());
            log.info("Connected");

            log.info("Get data");
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM" + as400ConfigProperties.getFile());
            while (resultSet.next()) {
                result.add(resultSet.getString("0"));
            }

            conn.close();
            log.info("Close connection");
        } catch (ClassNotFoundException | SQLException e) {
            log.error("AS400 Config Error: ", e);
        }

        return result;
    }

    public List<String> test() {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            result.add("Data " + i);
        }

        return result;
    }
}
