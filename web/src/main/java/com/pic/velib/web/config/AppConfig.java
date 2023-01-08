package com.pic.velib.web.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableJpaRepositories("com.pic.velib.repository")
@ComponentScan(basePackages = {"com.pic.velib.service.dto", "com.pic.velib.service.facebook", "com.pic.velib.service.recaptcha", "com.pic.velib.service.authentication"})
@EntityScan(basePackages = {"com.pic.velib.entity"})
public class AppConfig {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}


