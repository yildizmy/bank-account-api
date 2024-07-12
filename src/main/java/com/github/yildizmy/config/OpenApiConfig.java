package com.github.yildizmy.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class OpenApiConfig {

    @Value("${springdoc.dev-url}")
    String devUrl;

    @Bean
    public OpenAPI openApi() {
        Server devServer = new Server();
        devServer.setUrl("");
        devServer.setDescription("");

        Contact contact = new Contact();
        contact.setName("Murat Yıldız");
        contact.setEmail("");
        contact.setUrl("");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        io.swagger.v3.oas.models.info.Info info = new Info()
                .title("Bank Account API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage bank accounts.")
                .termsOfService("")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(Collections.emptyList());
    }
}
