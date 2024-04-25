package org.utl.conferencias;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class ConferenciasApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferenciasApplication.class, args);
    }
}
