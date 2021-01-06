package com.github.emarketplace.catalogservice.config;

import com.github.emarketplace.catalogservice.constants.OpenAPIConstants;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * @author Muhammed Shaheer
 * @since 04 January 2021
 */

@Configuration
public class OpenApiConfig {

    @Autowired
    Environment env;

    @Bean
    public OpenAPI openAPI() {
        String[] activeProfiles = env.getActiveProfiles();
        System.out.println(Arrays.toString(activeProfiles));
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title(OpenAPIConstants.API_SPEC_TITLE)
                        .description(OpenAPIConstants.API_SPEC_DESCRIPTION)
                        .version(OpenAPIConstants.API_SPEC_VERSION)
                );
    }
}
