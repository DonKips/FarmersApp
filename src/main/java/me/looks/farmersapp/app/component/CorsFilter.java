package me.looks.farmersapp.app.component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.looks.farmersapp.app.properties.CorsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CorsFilter implements Filter {

    private final CorsProperties corsProperties;

    @Autowired
    public CorsFilter(CorsProperties corsProperties) {
       this.corsProperties = corsProperties;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Access-Control-Allow-Origin", corsProperties.getAllowedOrigins());
        response.setHeader("Access-Control-Allow-Credentials", String.valueOf(corsProperties.getAllowCredentials()));
        response.setHeader("Access-Control-Allow-Methods", corsProperties.getAllowedMethods());
        response.setHeader("Access-Control-Max-Age", String.valueOf(corsProperties.getMaxAge()));
        response.setHeader("Access-Control-Allow-Headers", corsProperties.getAllowedHeaders());

        chain.doFilter(req, res);

    }
}