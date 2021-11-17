package edu.miu.ars.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public ApiInfo api() {
        return new ApiInfoBuilder()
                .title("Airline Reservation System")
                .version("1.0.0")
                .description("Api Documentation for Airline Reservation System")
                .termsOfServiceUrl("")
                .build();
    }
}
