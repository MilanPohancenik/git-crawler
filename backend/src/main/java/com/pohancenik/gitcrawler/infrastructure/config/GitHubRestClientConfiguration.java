package com.pohancenik.gitcrawler.infrastructure.config;

import com.pohancenik.gitcrawler.external.out.github.ApiClient;
import com.pohancenik.gitcrawler.external.out.github.api.SearchApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class GitHubRestClientConfiguration {

    @Bean
    public RestClient gitHubRestClient() {
        return RestClient
                .builder()
                .baseUrl("https://api.github.com")
                .defaultHeaders(headers -> {
                    headers.add("Accept", "application/vnd.github+json");
                    headers.add("X-GitHub-Api-Version", "2022-11-28");
                })
                .build();
    }

    @Bean
    public ApiClient gitHubApiClient() {
        return new ApiClient(gitHubRestClient());
    }

    @Bean
    public SearchApi gitHubSearchApi() {
        return new SearchApi(gitHubApiClient());
    }
}
