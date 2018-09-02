package com.apartdeman.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author Vahap Gencdal
 * @email avahap19@gmail.com
 * @date 31.08.2018
 *
 * @description :
 * @EnableSwagger2 enables SpringFox support for Swagger 2.
 * DocumentationType.SWAGGER_2 tells the Docket bean that we are using version 2 of Swagger specification.
 * select() creates a builder, which is used to define which controllers and which of their methods should be included in the generated documentation.
 * apis() defines the classes (controller and model classes) to be included. Here we are including all of them, but you can limit them by a base package,
 * class annotations and more.
 * paths() allow you to define which controller's methods should be included based on their path mappings. We are now including all of them but
 * you can limit it using regex and more.
 */
@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.apartdeman"))
                .paths(PathSelectors.ant("/api/v1/**"))
                .build();
    }

    @Bean
    public ApiInfo getApiInfo() {
        return new ApiInfo(
                "TITLE",
                "DESCIPRION",
                "VERSION",
                "TERMS OF SERVICE URL",
                new Contact("NAME","URL","EMAIL"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
}
