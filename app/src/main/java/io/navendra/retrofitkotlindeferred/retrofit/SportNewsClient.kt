package io.navendra.retrofitkotlindeferred.retrofit

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

object SportNewsClient : KoinComponent {

    private val SPORT_NEWS_BASE_URL: String by inject(named("SPORT_NEWS_BASE_URL"))

    private val retrofitClient: RetrofitClient by inject()

    val SPORT_NEWS_API : SportNewsApi by lazy {
        retrofitClient.retrofit(SPORT_NEWS_BASE_URL)
            .create(SportNewsApi::class.java)
    }

}