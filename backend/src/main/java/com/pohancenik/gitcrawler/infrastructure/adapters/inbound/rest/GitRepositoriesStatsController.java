package com.pohancenik.gitcrawler.infrastructure.adapters.inbound.rest;

import com.pohancenik.gitcrawler.application.ports.inbound.GitRepositoriesStatsUseCase;
import com.pohancenik.gitcrawler.domain.GitRepository;
import com.pohancenik.gitcrawler.domain.GitRepositoryStats;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${git-crawler.api.rest.api-public.path}/git-repositories-stats")
@AllArgsConstructor
public class GitRepositoriesStatsController {

    private final GitRepositoriesStatsUseCase gitRepositoriesStatsUseCase;

    @GetMapping("/top-by-stars")
    public List<GitRepository> topByStars(@RequestParam int maxRecords) {
        return gitRepositoriesStatsUseCase.topNByStars(maxRecords);
    }

    @GetMapping("/top-by-forks")
    public List<GitRepository> topByForks(@RequestParam int maxRecords) {
        return gitRepositoriesStatsUseCase.topNByForks(maxRecords);
    }

    @GetMapping("/top-by-pullrequest")
    public List<GitRepositoryStats> topByPullRequests(@RequestParam int maxRecords) {
        return gitRepositoriesStatsUseCase.topNByPullRequests(maxRecords);
    }

    @GetMapping("/top-by-contribution")
    public List<GitRepositoryStats> topByContribution(@RequestParam int maxRecords) {
        return gitRepositoriesStatsUseCase.topNByContribution(maxRecords);
    }

}
