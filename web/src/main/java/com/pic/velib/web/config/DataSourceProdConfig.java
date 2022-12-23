package com.pic.velib.web.config;

import com.pic.velib.service.properties.Properties;
import org.json.JSONObject;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.io.*;

@Configuration
@Profile("prod")
public class DataSourceProdConfig {

   private final  Properties properties = new Properties();
    @Bean
    public DataSource getDataSource() {

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();

        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url(properties.getString("database_url"));
        dataSourceBuilder.username(properties.getString("database_user"));
        dataSourceBuilder.password(properties.getString("database_password"));

        return dataSourceBuilder.build();
    }
}