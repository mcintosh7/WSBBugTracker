package com.example.projekt_dyplomowy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableScheduling
@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ProjektDyplomowyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjektDyplomowyApplication.class, args);
    }

}
