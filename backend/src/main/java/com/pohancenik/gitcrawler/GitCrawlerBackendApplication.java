package com.pohancenik.gitcrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class GitCrawlerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitCrawlerBackendApplication.class, args);
	}

}
