package com.pohancenik.gitcrawler.infrastructure.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClient;

@Configuration
public class GitHubRestClientConfiguration {

    @Value("${git-crawler.external.git-hub.authorization}")
    private String authorization;

    @Bean
    public ObjectMapper gitHubObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public RestClient gitHubRestClient() {
        return RestClient
                .builder()
                .baseUrl("https://api.github.com")
                .messageConverters(List.of(new MappingJackson2HttpMessageConverter(gitHubObjectMapper())))
                .defaultHeaders(headers -> {
                    headers.add(HttpHeaders.ACCEPT, "application/vnd.github+json");
                    headers.add(HttpHeaders.USER_AGENT, "com.pohancenik.gitcrawler");
                    headers.add("X-GitHub-Api-Version", "2022-11-28");
                    if (authorization != null && !authorization.isEmpty()) {
                        headers.add(HttpHeaders.AUTHORIZATION, "token " + authorization);
                    }
                })
                .build();
    }

}
