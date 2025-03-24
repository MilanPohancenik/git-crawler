package com.pohancenik.gitcrawler.infrastructure.adapters.outbound.rest.github.model;

import java.time.Instant;
import lombok.Data;

@Data
public class RepositorySearchResultItem {
    private Long id;
    private String fullName;
    private String htmlUrl;
    private String homepage;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private Integer forksCount;
    private Integer openIssuesCount;
}
