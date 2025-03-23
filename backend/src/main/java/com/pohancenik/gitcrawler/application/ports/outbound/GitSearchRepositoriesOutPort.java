package com.pohancenik.gitcrawler.application.ports.outbound;

import com.pohancenik.gitcrawler.domain.GitRepository;
import com.pohancenik.gitcrawler.domain.search.SearchCriteria;
import java.util.List;

public interface GitSearchRepositoriesOutPort {

    List<GitRepository> searchTopNByCriteria(SearchCriteria searchCriteria);

}
