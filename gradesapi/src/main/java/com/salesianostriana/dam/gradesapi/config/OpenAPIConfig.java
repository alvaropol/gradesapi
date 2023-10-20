package com.salesianostriana.dam.gradesapi.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openApi(){
        return new OpenAPI().info(new Info()
                        .title("Grades API")
                        .description("API Rest que gestiona alumnos, asignaturas, instrumentos y calificaciones.")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("https://httpd.apache.org/docs/")))
                .externalDocs(new ExternalDocumentation().description("Wiki")
                        .url("https://github.com/alvaropol/gradesapi"));
    }
}