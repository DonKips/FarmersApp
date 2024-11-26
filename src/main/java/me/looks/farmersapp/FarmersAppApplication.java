package me.looks.farmersapp;

import me.looks.farmersapp.app.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({CorsProperties.class})
public class FarmersAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FarmersAppApplication.class, args);
    }

}
