package com.pic.velib.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("prod")
public class DataSourceProdConfig {


    @Value("${database_url}")
    private String database_url;


    @Value("${database_user}")
    private String database_user;


    @Value("${database_password}")
    private String database_password;

    @Bean
    public DataSource getDataSource() {

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();

        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url(database_url);
        dataSourceBuilder.username(database_user);
        dataSourceBuilder.password(database_password);

        return dataSourceBuilder.build();
    }
}