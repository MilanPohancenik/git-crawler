package com.pohancenik.gitcrawler.application.service;

import com.pohancenik.gitcrawler.application.ports.inbound.GitRepositoriesStatsUseCase;
import com.pohancenik.gitcrawler.application.ports.outbound.git.GitSearchRepositoriesOutPort;
import com.pohancenik.gitcrawler.application.ports.outbound.git.SearchCriteria;
import com.pohancenik.gitcrawler.domain.GitRepository;
import com.pohancenik.gitcrawler.domain.GitRepositoryStats;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
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
    public List<GitRepositoryStats> topNByPullRequests(int count) {
        // get top-n by stars
        List<GitRepository> staredRepositories = topNByStars(count);

        // now get the PR count + calculate contribution and sort according to PR
        return getSortedStatsWithPrCountAndCalculateContributionPercentage(staredRepositories,
                Comparator.comparingInt(GitRepositoryStats::countOfPr));
    }

    @Override
    public List<GitRepositoryStats> topNByContribution(int count) {
        // get top-n by stars
        List<GitRepository> staredRepositories = topNByStars(count);

        // now get the PR count + calculate contribution and sort according to PR
        return getSortedStatsWithPrCountAndCalculateContributionPercentage(staredRepositories,
                Comparator.comparing(GitRepositoryStats::contributionPercentage));
    }

    private List<GitRepositoryStats> getSortedStatsWithPrCountAndCalculateContributionPercentage(List<GitRepository> repositories,
                                                                                                 Comparator<GitRepositoryStats> comparator) {

        List<GitRepositoryStats> gitRepositoryStats = new ArrayList<>();
        repositories.forEach(gitRepository -> {
            Integer prCount = gitSearchRepositoriesOutPort.countPRsForRepository(gitRepository.name());
            gitRepositoryStats.add(
                    new GitRepositoryStats(
                            prCount,
                            gitRepository.forksCount() > 0 ?
                                    BigDecimal.valueOf((double) prCount / (double) gitRepository.forksCount() * 100) : BigDecimal.ZERO,
                            gitRepository));
        });
        gitRepositoryStats.sort(comparator);
        return gitRepositoryStats;
    }
}
