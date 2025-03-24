package com.pohancenik.gitcrawler.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Git Crawler backend",
                version = "1.0",
                description = "This app scans through the GIT apis and tries to collect some metrics"
        )
)
public class SpringDocConfiguration {

    @Bean
    public GroupedOpenApi businessApi() {
        return GroupedOpenApi.builder()
                .group("github-seatch")
                .pathsToMatch("/api/public/rest/**")
                .build();
    }

    @Bean
    public GroupedOpenApi actuatorApi() {
        return GroupedOpenApi.builder()
                .group("actuator-api")
                .pathsToMatch("/actuator/**")
                .build();
    }

}
