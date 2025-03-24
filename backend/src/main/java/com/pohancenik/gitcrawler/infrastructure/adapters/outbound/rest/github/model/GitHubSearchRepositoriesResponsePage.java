package com.pohancenik.gitcrawler.infrastructure.adapters.outbound.rest.github.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class GitHubSearchRepositoriesResponsePage {
    private long totalCount;
    private List<RepositorySearchResultItem> items = new ArrayList<>();
}
