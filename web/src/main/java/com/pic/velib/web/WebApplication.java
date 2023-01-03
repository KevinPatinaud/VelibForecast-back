package com.pic.velib.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Collections;
import java.util.Map;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WebApplication.class);

        Map defaultProperties = Collections.singletonMap("server.port", "8083");
        defaultProperties.put("server.ssl.enabled", "true");
        defaultProperties.put("server.ssl.keyAlias", "tomcat");
        defaultProperties.put("server.ssl.key-store", "/etc/letsencrypt/live/velib-forecast.com/keystore.p12");
        defaultProperties.put("server.ssl.key-store-password", "Hack4Ever!");
        defaultProperties.put("server.ssl.keyStoreType", "PKCS12");
        app.setDefaultProperties(defaultProperties);

        app.run(args);

    }

}
