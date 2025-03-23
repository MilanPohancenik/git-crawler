package com.pohancenik.gitcrawler.application.service;

import com.pohancenik.gitcrawler.application.ports.inbound.GitRepositoriesStatsUseCase;
import com.pohancenik.gitcrawler.application.ports.outbound.GitSearchRepositoriesOutPort;
import com.pohancenik.gitcrawler.domain.GitRepository;
import com.pohancenik.gitcrawler.domain.search.SearchCriteria;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GitRepositoriesStatsService implements GitRepositoriesStatsUseCase {

    private final GitSearchRepositoriesOutPort gitSearchRepositoriesOutPort;

    @Override
    public List<GitRepository> topNByStars(int count) {
        return gitSearchRepositoriesOutPort.searchTopNByCriteria(
                SearchCriteria.builder()
                        .orderCriteria(SearchCriteria.OrderCriteria.NUMBER_OF_STARS)
                        .maxRecords(count).build());
    }

    @Override
    public List<GitRepository> topNByForks(int count) {
        return gitSearchRepositoriesOutPort.searchTopNByCriteria(
                SearchCriteria.builder()
                        .orderCriteria(SearchCriteria.OrderCriteria.NUMBER_OF_FORKS)
                        .maxRecords(count).build());
    }

    @Override
    public List<GitRepository> topNByPullRequests(int count) {
        // needs custom implementation
//        return gitRepositoriesOutPort.findBy(
//                SearchCriteria.builder()
//                        .orderCriteria(SearchCriteria.OrderCriteria.NUMBER_OF_PULLREQUESTS)
//                        .maxRecords(count).build());
        throw new UnsupportedOperationException();
    }

    @Override
    public List<GitRepository> topNByContribution(int count) {
//        List<String> topForks = topNByForks(count);

        // for each fork, get amount of PRs

        // calculate percentage
        throw new UnsupportedOperationException();
    }
}
