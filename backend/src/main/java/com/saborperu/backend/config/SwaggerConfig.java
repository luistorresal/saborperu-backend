package com.saborperu.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SaborPerú API")
                        .version("1.0")
                        .description("API REST para la tienda de productos peruanos SaborPerú. " +
                                "Incluye operaciones CRUD para productos, categorías y usuarios.")
                        .contact(new Contact()
                                .name("SaborPerú Team")
                                .email("contacto@saborperu.com")));
    }
}

