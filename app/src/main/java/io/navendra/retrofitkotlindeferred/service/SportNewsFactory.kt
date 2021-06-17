package io.navendra.retrofitkotlindeferred.service

import io.navendra.retrofitkotlindeferred.AppConstants

object SportNewsFactory{

    val SPORT_NEWS_API : SportNewsApi = RetrofitFactory.retrofit(AppConstants.SPORT_NEWS_BASE_URL)
                                                .create(SportNewsApi::class.java)

}