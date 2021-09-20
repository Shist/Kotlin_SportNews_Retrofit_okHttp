package io.navendra.retrofitkotlindeferred.ui.repository

import android.content.Context
import android.util.Log
import androidx.room.Room
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsApi
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsClient
import io.navendra.retrofitkotlindeferred.roomDB.NewsDatabase
import io.navendra.retrofitkotlindeferred.roomDB.entities.NewsItemDB
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

    suspend fun loadNews() {
        val latestNews: List<NewsItemDB> =
            service.getNews().items.map { NewsItemsMapper.fromJsonToRoomDB(it) }
        newsDatabase.itemsDao().insertItemsList(latestNews)
    }

    suspend fun loadNewsPageByID(itemID: String) : NewsItemDB? {
        var item: NewsItemDB? = null

        try {
            val latestNews = service.getNews().items.map { NewsItemsMapper.fromJsonToRoomDB(it) }
            item = latestNews.find { it.itemId == itemID }

            if (item != null) {

                // На будущее: нужно будет сделать отдельную таблицу для айтема
                newsDatabase.itemsDao().insertOneItem(item)
            } else {
                // something...
            }
        } catch (e: Throwable) {
            // something...
        }

        return item
    }

    fun getItems(): Flow<List<NewsItemDB>> {
        return newsDatabase.itemsDao().getAllItems()
    }

    fun getItemByID(itemId: String): Flow<NewsItemDB> {
        return newsDatabase.itemsDao().getItemById(itemId)
    }

}
