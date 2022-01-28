package com.target.targetcasestudy.data;

import com.target.targetcasestudy.data.parsers.Deals;
import io.reactivex.Completable;
import io.reactivex.Single;
import javax.inject.Inject;


public class DealsRepository {

    private final DealAPIService dealAPIService;

    @Inject
    public DealsRepository(DealAPIService dealAPIService) {
        this.dealAPIService = dealAPIService;
    }

    public Single<Deals> getDeals() {
        return dealAPIService.getDeals();
    }
}