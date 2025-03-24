package com.pohancenik.gitcrawler.infrastructure.adapters.outbound.rest.github.mapper;

import com.pohancenik.gitcrawler.domain.GitRepository;
import com.pohancenik.gitcrawler.infrastructure.adapters.outbound.rest.github.model.RepositorySearchResultItem;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GitHubRepositoryMapper {

    @Mapping(source = "htmlUrl", target = "url")
    @Mapping(source = "fullName", target = "name")
    GitRepository mapToGitRepository(RepositorySearchResultItem repositorySearchResultItem);

    List<GitRepository> mapToGitRepository(List<RepositorySearchResultItem> searchResultItem);
}
