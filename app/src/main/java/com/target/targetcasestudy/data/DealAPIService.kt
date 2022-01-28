package com.target.targetcasestudy.data

import com.target.targetcasestudy.data.parsers.Deals
import io.reactivex.Single
import retrofit2.http.GET

interface DealAPIService {

  @GET("mobile_case_study_deals/v1/deals")
  fun getDeals(): Single<Deals>

}