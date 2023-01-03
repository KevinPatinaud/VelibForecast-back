package com.pic.velib.web.config;

import com.pic.velib.service.properties.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("prod")
@ComponentScan("com.pic.velib.service.properties")
public class DataSourceProdConfig {

    @Autowired
   private final Properties properties;

    public DataSourceProdConfig(Properties properties) {
        this.properties = properties;
    }

    @Bean
    public DataSource getDataSource() {

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();

        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url(properties.getDatabaseURL());
        dataSourceBuilder.username(properties.getDatabaseUser());
        dataSourceBuilder.password(properties.getDatabasePassword());

        return dataSourceBuilder.build();
    }
}