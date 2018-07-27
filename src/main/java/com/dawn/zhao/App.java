package com.dawn.zhao;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(Application.class, args);
    }
}
