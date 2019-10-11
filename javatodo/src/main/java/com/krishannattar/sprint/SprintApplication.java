package com.krishannattar.sprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SprintApplication {

    public static void main(String[] args) {
        SpringApplication.run(SprintApplication.class, args);
    }

}
