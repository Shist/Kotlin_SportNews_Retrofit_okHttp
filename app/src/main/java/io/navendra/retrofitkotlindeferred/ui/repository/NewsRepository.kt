package io.navendra.retrofitkotlindeferred.ui.repository

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsApi
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsClient
import io.navendra.retrofitkotlindeferred.roomDB.MigrationDB
import io.navendra.retrofitkotlindeferred.roomDB.NewsItemDatabase
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem.NewsItemTable
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem.NewsItemMapper
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails.NewsItemDetailsMapper
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails.NewsItemDetailsTable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(@ApplicationContext context: Context) {

    private val newsItemDatabase: NewsItemDatabase = buildDatabase(context)

    private fun buildDatabase(context: Context) =
        Room.databaseBuilder(context.applicationContext,
            NewsItemDatabase::class.java, "newsDB")
            .addMigrations(MigrationDB.MIGRATION_1_2)
            .build()

    private val service: SportNewsApi = SportNewsClient.SPORT_NEWS_API

    suspend fun loadNews() {
        newsItemDatabase.itemsDao().insertItemsList(service.getNews().items
            .map { NewsItemMapper.fromJsonToRoomDB(it) })
        newsItemDatabase.itemsDetailsDao().insertItemsDetailsList(service.getNewsDetails().itemsDetails
            .map { NewsItemDetailsMapper.fromJsonToRoomDB(it) })
    }

    suspend fun loadNewsItemDetailsByID(itemID: String) : NewsItemDetailsTable? {
        var itemDetails: NewsItemDetailsTable? = null

        val latestNews = service.getNewsDetails().itemsDetails
            .map { NewsItemDetailsMapper.fromJsonToRoomDB(it) }
        itemDetails = latestNews.find { it.itemId == itemID }

        if (itemDetails != null) {
            newsItemDatabase.itemsDetailsDao().insertOneItemDetails(itemDetails)
        } else {
            // TODO make some snackbar with error
        }

        return itemDetails
    }

    fun getItems(): Flow<List<NewsItemTable>> {
        return newsItemDatabase.itemsDao().getAllItems()
    }

    fun getItemDetailsByID(itemDetailsId: String): Flow<NewsItemDetailsTable> {
        return newsItemDatabase.itemsDetailsDao().getItemDetailsById(itemDetailsId)
    }

}
