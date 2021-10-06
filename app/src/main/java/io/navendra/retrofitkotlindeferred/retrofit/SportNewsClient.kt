package io.navendra.retrofitkotlindeferred.retrofit

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

class SportNewsClient : KoinComponent {

    private val sportNewsBaseUrl: String by inject(named("SPORT_NEWS_BASE_URL"))

    private val retrofitClient: RetrofitClient by inject()

    val sportNewsApi : SportNewsApi by lazy {
        retrofitClient.retrofit(sportNewsBaseUrl)
            .create(SportNewsApi::class.java)
    }

}