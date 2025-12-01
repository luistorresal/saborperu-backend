package com.saborperu.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(
                    "http://localhost:3000",           // Desarrollo local
                    "http://44.199.127.47",            // Frontend EC2 IP Elástica (puerto 80)
                    "http://44.199.127.47:80",         // Frontend EC2 IP Elástica explícito
                    "http://44.199.127.47:3000"        // Frontend EC2 IP Elástica (puerto 3000)
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}




