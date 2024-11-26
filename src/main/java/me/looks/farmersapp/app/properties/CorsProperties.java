package me.looks.farmersapp.app.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {

    private String allowedOrigins;
    private String allowedMethods;
    private String allowedHeaders;

    private Boolean allowCredentials;

    private Long maxAge;

}