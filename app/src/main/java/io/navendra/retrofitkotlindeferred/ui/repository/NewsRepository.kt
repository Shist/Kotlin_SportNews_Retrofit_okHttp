package io.navendra.retrofitkotlindeferred.ui.repository

import android.util.Log
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsApi
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsClient

object NewsRepository {

    private val service: SportNewsApi = SportNewsClient.SPORT_NEWS_API

    suspend fun loadNews(): List<NewsItem> {
        var latestNews: List<NewsItem> = emptyList()

        try {
            latestNews = service.getNews().items

            if(latestNews.isNotEmpty()) {
                Log.d("MyLog", "Loading news to NewsRepository...")
                Log.d("MyLog", "Response: ${latestNews.size} items...")
                for (i in latestNews.indices)
                    Log.d("MyLog", latestNews[i].toString())
            }else {
                Log.d("MyLog", "Failure while loading news to NewsRepository...")
                Log.d("MyLog", "Reason: we've got empty list...")
            }
        } catch (e: Throwable) {
            Log.d("MyLog", "Failure while loading news to NewsRepository...")
            Log.d("MyLog", "Reason: ", e)
        }

        return latestNews
    }

    suspend fun getNewsPageByID(item_id: String) : NewsItem? {
        var item: NewsItem? = null

        try {
            val latestNews: List<NewsItem> = service.getNews().items
            item = latestNews.find { it.itemId == item_id }

            if(item != null) {
                Log.d("MyLog", "Loading item from list...")
                Log.d("MyLog", "Response item: $item")
            } else {
                Log.d("MyLog", "Failure while loading item from list...")
                Log.d("MyLog", "Reason: we've got empty item")
            }
        } catch (e: Throwable) {
            Log.d("MyLog", "Failure while loading item from list...")
            Log.d("MyLog", "Reason: ", e)
        }

        return item
    }

}
