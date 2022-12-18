package com.pic.velib.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Collections;


@SpringBootApplication
@EnableScheduling
public class BatchApplication {

    public static void main(String[] args) {
 //       SpringApplication.run(BatchApplication.class, args);


        SpringApplication app = new SpringApplication(BatchApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8085"));
        app.run(args);
    }

}
