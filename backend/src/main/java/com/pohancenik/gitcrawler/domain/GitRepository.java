package com.pohancenik.gitcrawler.domain;

import java.net.URI;

public record GitRepository(
        String id,
        String name,
        URI url,
        URI homepage,
        String description,
        String forksCount,
        String openIssuesCount) {
}
