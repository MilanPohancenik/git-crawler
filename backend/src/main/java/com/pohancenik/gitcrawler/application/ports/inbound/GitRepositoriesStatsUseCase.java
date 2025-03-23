package com.pohancenik.gitcrawler.application.ports.inbound;

import com.pohancenik.gitcrawler.domain.GitRepository;
import java.util.List;

/**
 * Describes the API for getting repositories from Git
 */
public interface GitRepositoriesStatsUseCase {

    /** Top-N repos by number of stars. */
    List<GitRepository> topNByStars(int count);

    /** Top-N repos by number of forks. */
    List<GitRepository> topNByForks(int count);

    /** Top-GitRepository repos by number of Pull Requests (PRs). */
    List<GitRepository> topNByPullRequests(int count);

    /**
     * <b>(Optional) Top-N repos by contribution percentage (PRs/forks).</b>
     * This requires us to pull repos with highest? forks and corresponding PRs and calculate the percentage.
     */
    List<GitRepository> topNByContribution(int count);

}
