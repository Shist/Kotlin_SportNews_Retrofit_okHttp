package io.navendra.retrofitkotlindeferred.ui.repository

import io.navendra.retrofitkotlindeferred.retrofit.SportNewsApi
import io.navendra.retrofitkotlindeferred.roomDB.NewsItemDatabase
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem.NewsItemTable
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem.NewsItemMapper
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails.NewsItemDetailsMapper
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails.NewsItemDetailsTable
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NewsRepository(private val newsItemDatabase: NewsItemDatabase,
                     private val service: SportNewsApi) : KoinComponent {

    private val newsItemMapper: NewsItemMapper by inject()

    private val newsItemDetailsMapper: NewsItemDetailsMapper by inject()

    suspend fun loadNews() {
        newsItemDatabase.itemsDao().insertItemsList(service.getNews().items
            .map { newsItemMapper.fromJsonToRoomDB(it) })
        newsItemDatabase.itemsDetailsDao().insertItemsDetailsList(service.getNewsDetails().itemsDetails
            .map { newsItemDetailsMapper.fromJsonToRoomDB(it) })
    }

    suspend fun loadNewsItemDetailsByID(itemID: String) : NewsItemDetailsTable? {
        var itemDetails: NewsItemDetailsTable? = null

        val latestNews = service.getNewsDetails().itemsDetails
            .map { newsItemDetailsMapper.fromJsonToRoomDB(it) }
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
