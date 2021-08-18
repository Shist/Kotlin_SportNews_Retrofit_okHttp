package io.navendra.retrofitkotlindeferred.ui.repository

import android.content.Context
import android.util.Log
import androidx.room.Room
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsApi
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsClient
import io.navendra.retrofitkotlindeferred.roomDB.NewsDatabase

class NewsRepository {

    companion object {
        private var instance: NewsDatabase? = null

        fun getInstance(context: Context): NewsDatabase =
            instance?: synchronized(this) {
                instance?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                NewsDatabase::class.java, "newsDB")
                .build()

    }

    private val service: SportNewsApi = SportNewsClient.SPORT_NEWS_API

    suspend fun loadNews(): List<NewsItem> {
        var latestNews: List<NewsItem> = emptyList()


        // 2 метода
        // 1-ый: loadNews, который дезагружает данные и кладет в бд
        // 2-ой: делаем getNewsFlow, который просто будет брать у DAO flow

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
            item = latestNews.find { it.id == item_id }

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
