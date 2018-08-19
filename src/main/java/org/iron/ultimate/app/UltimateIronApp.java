package org.iron.ultimate.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("org.iron.ultimate")
@EnableJpaRepositories("org.iron.ultimate.jpa.dao.repository")
@EnableScheduling
public class UltimateIronApp {
	
    public static void main(String[] args) {
        SpringApplication.run(UltimateIronApp.class, args);
    }
    
}
