package com.pohancenik.gitcrawler.infrastructure.adapters.outbound.rest;

import com.pohancenik.gitcrawler.application.ports.outbound.GitSearchRepositoriesOutPort;
import com.pohancenik.gitcrawler.domain.GitRepository;
import com.pohancenik.gitcrawler.domain.search.SearchCriteria;
import com.pohancenik.gitcrawler.external.out.github.api.SearchApi;
import com.pohancenik.gitcrawler.external.out.github.model.SearchRepos200Response;
import com.pohancenik.gitcrawler.mapper.GitHubRepositoryMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class GitHubSearchRepositoriesRestApi implements GitSearchRepositoriesOutPort {

    private final SearchApi gitHubSearchApi;

    private final GitHubRepositoryMapper gitHubRepositoryMapper;

    /**
     * @see <a href="https://docs.github.com/en/rest/search/search?apiVersion=2022-11-28#search-repositories">Documentation of repositories search</a>
     * @see <a href="https://docs.github.com/en/search-github/searching-on-github/searching-for-repositories">Search query syntax in repositories</a>
     * <p>
     * <b>Example request:</b>
     * <code>
     * curl -L \
     * -H "Accept: application/vnd.github+json" \
     * -H "Authorization: Bearer <YOUR-TOKEN>" \
     * -H "X-GitHub-Api-Version: 2022-11-28" \
     * "https://api.github.com/search/repositories?q=Q"
     * </code>
     * </p>
     * Where q can be something like "is:pr" "is:open" ...
     */
    @Override
    public List<GitRepository> searchTopNByCriteria(SearchCriteria searchCriteria) {
        SearchRepos200Response response =
                gitHubSearchApi.searchRepos("Q", searchCriteria.getOrderCriteria().getOrderBy(), "desc", searchCriteria.getMaxRecords(), 1);
        return gitHubRepositoryMapper.mapToGitRepository(response.getItems());
    }

}
