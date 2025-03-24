package com.pohancenik.gitcrawler.application.ports.outbound.git;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchCriteria {

    @Getter
    public enum OrderCriteria {
        NUMBER_OF_FORKS("forks"), NUMBER_OF_STARS("stars");

        private final String orderBy;
        OrderCriteria(String orderBy) {
            this.orderBy = orderBy;
        }
    }

    private OrderCriteria orderCriteria;
    private int maxRecords;

}
