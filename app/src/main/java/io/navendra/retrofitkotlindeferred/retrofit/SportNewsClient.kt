package io.navendra.retrofitkotlindeferred.retrofit

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object SportNewsClient : KoinComponent {

    private const val SPORT_NEWS_BASE_URL = "https://api.beinsports.com/"

    private val retrofitClient: RetrofitClient by inject()

    val SPORT_NEWS_API : SportNewsApi by lazy {
        retrofitClient.retrofit(SPORT_NEWS_BASE_URL)
            .create(SportNewsApi::class.java)
    }

}