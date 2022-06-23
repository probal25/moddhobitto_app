package com.probal.moddhobitto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(getApiInfo());
    }

    private Info getApiInfo() {
        return new Info()
                .title("Moddhobitto Backend API")
                .version("1.0")
                .description("Rest Api server for Moddhobitto Application")
                .contact(getContactInfo())
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));
    }

    private Contact getContactInfo() {

        return new Contact()
                .name("Ahamed Rashid Probal")
                .email("probal25@hotmail.com")
                .url("https://github.com/probal25");
    }

}
