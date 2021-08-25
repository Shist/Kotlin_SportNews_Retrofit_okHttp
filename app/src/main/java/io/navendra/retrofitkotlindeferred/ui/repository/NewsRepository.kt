package io.navendra.retrofitkotlindeferred.ui.repository

import android.content.Context
import android.util.Log
import androidx.room.Room
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsApi
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsClient
import io.navendra.retrofitkotlindeferred.roomDB.NewsDatabase
import io.navendra.retrofitkotlindeferred.roomDB.entities.NewsItemsDB
import io.navendra.retrofitkotlindeferred.roomDB.entities.NewsItemsMapper
import kotlinx.coroutines.flow.Flow

class NewsRepository(context: Context) {

    companion object {
        private var newsRepository: NewsRepository? = null

        fun getInstance(context: Context): NewsRepository
        {
            return newsRepository ?: synchronized(this) {
                newsRepository ?: NewsRepository(context).also { newsRepository = it }
            }
        }
    }

    private val newsDatabase: NewsDatabase = buildDatabase(context)



    private fun buildDatabase(context: Context) =
        Room.databaseBuilder(context.applicationContext,
            NewsDatabase::class.java, "newsDB")
            .build()

    private val service: SportNewsApi = SportNewsClient.SPORT_NEWS_API

    suspend fun loadNews(): List<NewsItem> {
        var latestNews: List<NewsItem> = emptyList()

        // 2 метода
        // 1-ый: loadNews, который дезагружает данные и кладет в бд
        // 2-ой: делаем getNewsFlow, который просто будет брать у DAO flow

        try {
            latestNews = service.getNews().items
            newsDatabase!!.itemsDao().insertItemsList(NewsItemsMapper.listFromJsonToRoomDB(latestNews))

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

    fun getItems(): Flow<List<NewsItemsDB>> {
        return newsDatabase?.itemsDao()?.getAllItems()
    }

    fun getItemByID(item_id: String): Flow<NewsItemsDB?> {
        return newsDatabase?.itemsDao()?.getItemById(item_id)
    }

}
