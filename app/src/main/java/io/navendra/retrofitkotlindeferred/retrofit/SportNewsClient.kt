package io.navendra.retrofitkotlindeferred.retrofit

object SportNewsClient{

    private const val SPORT_NEWS_BASE_URL = "https://api.beinsports.com/"
    val SPORT_NEWS_API : SportNewsApi by lazy {
        RetrofitClient.retrofit(SPORT_NEWS_BASE_URL)
            .create(SportNewsApi::class.java)
    }

}