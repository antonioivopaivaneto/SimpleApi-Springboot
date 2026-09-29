package com.trabalho.clientes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("API de Clientes e Endereços")
                        .version("1.0")
                        .description(
                                "API REST desenvolvida para o trabalho de Backend Java OO. " +
                                "Permite realizar CRUD de clientes e seus respectivos endereços."
                        )
                );
    }
}