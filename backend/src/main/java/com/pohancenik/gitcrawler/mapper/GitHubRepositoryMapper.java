package com.pohancenik.gitcrawler.mapper;

import com.pohancenik.gitcrawler.domain.GitRepository;
import com.pohancenik.gitcrawler.external.out.github.model.RepoSearchResultItem;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GitHubRepositoryMapper {

    @Mapping(source = "htmlUrl", target = "url")
    GitRepository mapToGitRepository(RepoSearchResultItem searchResultItem);

    List<GitRepository> mapToGitRepository(List<RepoSearchResultItem> searchResultItem);
}
