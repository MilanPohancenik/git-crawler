package com.pohancenik.gitcrawler.infrastructure.adapters.inbound.rest;

import com.pohancenik.gitcrawler.application.ports.inbound.GitRepositoriesStatsUseCase;
import com.pohancenik.gitcrawler.domain.GitRepository;
import com.pohancenik.gitcrawler.domain.GitRepositoryStats;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(summary = "Retrieve top N repositories sorted by amount of stars")
    @GetMapping("/top-by-stars")
    public List<GitRepository> topByStars(
            @Parameter(description = "The max amount of records to return", example = "5")
            @RequestParam int maxRecords) {
        return gitRepositoriesStatsUseCase.topNByStars(maxRecords);
    }

    @Operation(summary = "Retrieve top N repositories sorted by number of forks")
    @GetMapping("/top-by-forks")
    public List<GitRepository> topByForks(
            @Parameter(description = "The max amount of records to return", example = "5")
            @RequestParam int maxRecords) {
        return gitRepositoriesStatsUseCase.topNByForks(maxRecords);
    }

    @Operation(summary = "Retrieve top N repositories and sort them by pull request count")
    @GetMapping("/top-by-pullrequest")
    public List<GitRepositoryStats> topByPullRequests(
            @Parameter(description = "The max amount of records to return", example = "5")
            @RequestParam int maxRecords) {
        return gitRepositoriesStatsUseCase.topNByPullRequests(maxRecords);
    }

    @Operation(summary = "Retrieve top N repositories and sort them by contribution percentage")
    @GetMapping("/top-by-contribution")
    public List<GitRepositoryStats> topByContribution(
            @Parameter(description = "The max amount of records to return", example = "5")
            @RequestParam int maxRecords) {
        return gitRepositoriesStatsUseCase.topNByContribution(maxRecords);
    }

}
