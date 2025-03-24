package com.pohancenik.gitcrawler.infrastructure.adapters.outbound.rest.github;

import com.pohancenik.gitcrawler.application.ports.outbound.git.GitSearchRepositoriesOutPort;
import com.pohancenik.gitcrawler.application.ports.outbound.git.SearchCriteria;
import com.pohancenik.gitcrawler.domain.GitRepository;
import com.pohancenik.gitcrawler.infrastructure.adapters.outbound.rest.github.model.GitHubSearchIssuesResponsePage;
import com.pohancenik.gitcrawler.infrastructure.adapters.outbound.rest.github.model.GitHubSearchRepositoriesResponsePage;
import com.pohancenik.gitcrawler.mapper.GitHubRepositoryMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
@AllArgsConstructor
public class GitHubSearchRepositoriesRestApi implements GitSearchRepositoriesOutPort {

    private final RestClient gitHubRestClient;
    private final GitHubRepositoryMapper gitHubRepositoryMapper;

    private static final String Q = "q";
    private static final String ORDER = "order";
    private static final String SORT = "sort";
    private static final String PER_PAGE = "per_page";
    private static final String PAGE = "page";

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
     * -H "User-Agent: YOUR_APP_NAME" \
     * "https://api.github.com/search/repositories?q=Q"
     * </code>
     * </p>
     * Where q can be something like "is:pr" "is:open" ...
     */
    @Override
    public List<GitRepository> searchTopNByCriteria(SearchCriteria searchCriteria) {
        log.info("Sorting based on {}, with limit {}", searchCriteria.getOrderCriteria(), searchCriteria.getMaxRecords());
        GitHubSearchRepositoriesResponsePage response = gitHubRestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/search/repositories")
                                .queryParam(Q, "forks:>0 stars:>0")
                                .queryParam(ORDER, searchCriteria.getOrderCriteria().getOrderBy())
                                .queryParam(SORT, "desc")
                                .queryParam(PER_PAGE, searchCriteria.getMaxRecords())
                                .queryParam(PAGE, 1)
                                .build()
                ).retrieve()
                .body(GitHubSearchRepositoriesResponsePage.class);
        if (response == null) {
            return new ArrayList<>();
        }
        return gitHubRepositoryMapper.mapToGitRepository(response.getItems());
    }

    @Override
    public Integer countPRsForRepository(String repositoryName) {
        log.info("Searching for repository {}", repositoryName);
        Integer prCount = 0;

        // get the open PRs
        GitHubSearchIssuesResponsePage openPRresponse = searchPrsForRepository(repositoryName, true);
        if (openPRresponse != null) {
            prCount += openPRresponse.getTotalCount();
        }

        // get the closed PRs
        GitHubSearchIssuesResponsePage closedPRresponse = searchPrsForRepository(repositoryName, false);
        if (closedPRresponse != null) {
            prCount += closedPRresponse.getTotalCount();
        }

        return prCount;
    }

    private GitHubSearchIssuesResponsePage searchPrsForRepository(String repositoryName, boolean onlyOpen) {
        return gitHubRestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/search/issues")
                                .queryParam(Q,
                                        "repo:" + repositoryName + " is:pr " + (onlyOpen ? "is:open" : "is:closed"))
                                .queryParam(ORDER, "stars")
                                .queryParam(SORT, "desc")
                                .queryParam(PER_PAGE, 1)
                                .queryParam(PAGE, 1)
                                .build()
                ).retrieve()
                .body(GitHubSearchIssuesResponsePage.class);
    }
}
