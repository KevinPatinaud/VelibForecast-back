package com.pic.velib.web;

import com.pic.velib.service.properties.Properties;
import com.pic.velib.service.properties.PropertiesImpl;
import com.pic.velib.web.config.AppConfig;
import com.pic.velib.web.config.DataSourceDevConfig;
import com.pic.velib.web.config.DataSourceProdConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class WebApplication {

    private static final PropertiesImpl properties = new PropertiesImpl();


    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(WebApplication.class);

         app.setDefaultProperties(properties.getSpringAppDefaultProperties());

        app.run(args);

    }

}
