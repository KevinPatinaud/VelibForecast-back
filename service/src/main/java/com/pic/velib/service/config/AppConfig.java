package com.pic.velib.service.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.pic.velib.repository")
@EntityScan(basePackages = {"com.pic.velib.entity"})
@ComponentScan(basePackages = {"com.pic.velib.service.facebook"})
public class AppConfig {
}


