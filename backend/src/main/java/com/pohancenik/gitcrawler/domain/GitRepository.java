package com.pohancenik.gitcrawler.domain;

public record GitRepository(
        String id,
        String name,
        String url,
        String homepage,
        String description,
        Integer forksCount,
        Integer openIssuesCount) {
}
