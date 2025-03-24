package com.pohancenik.gitcrawler.application.ports.outbound.git;

import com.pohancenik.gitcrawler.domain.GitRepository;
import java.util.List;

public interface GitSearchRepositoriesOutPort {

    /**
     * Should search through git repositories and return the SearchCriteria.maxRecords at most and sort the result according to "stars" or "forks".
     */
    List<GitRepository> searchTopNByCriteria(SearchCriteria searchCriteria);

    /**
     * Should get the count of PRs for a repository
     */
    Integer countPRsForRepository(String repositoryName);

}
