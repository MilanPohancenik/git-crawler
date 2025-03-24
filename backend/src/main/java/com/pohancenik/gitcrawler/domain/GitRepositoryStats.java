package com.pohancenik.gitcrawler.domain;

import java.math.BigDecimal;

public record GitRepositoryStats(Integer countOfPr, BigDecimal contributionPercentage, GitRepository repository) {
}
