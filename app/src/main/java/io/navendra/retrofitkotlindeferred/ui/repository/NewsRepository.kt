package io.navendra.retrofitkotlindeferred.ui.repository

import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.model.NewsItemDetails
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

    private fun isItemWithID(item: NewsItem): Boolean {
        return item.id != null
    }

    private fun isItemWithID(item: NewsItemDetails): Boolean {
        return item.id != null
    }

    private fun isItemNotEmpty(item: NewsItemTable): Boolean {
        return item.altText != null ||
                item.context != null ||
                item.shortHeadline != null
    }

    private fun isItemNotEmpty(item: NewsItemDetailsTable): Boolean {
        return item.body != null ||
                item.context != null ||
                item.shortHeadline != null
    }

    suspend fun loadNews() {
        try {
            newsItemDatabase.itemsDao().insertItemsList(service.getNews().items!!
                .filter { isItemWithID(it) }
                .map { newsItemMapper.fromJsonToRoomDB(it) }
                .filter { isItemNotEmpty(it) })
            newsItemDatabase.itemsDetailsDao().insertItemsDetailsList(service.getNewsDetails().itemsDetails!!
                .filter { isItemWithID(it) }
                .map { newsItemDetailsMapper.fromJsonToRoomDB(it) }
                .filter { isItemNotEmpty(it) })
        } catch (e: Throwable) {
            // TODO make some snackbar with error "There is empty item list received from json API"
        }
    }

    suspend fun loadNewsItemDetailsByID(itemID: String) : NewsItemDetailsTable? {
        var itemDetails: NewsItemDetailsTable? = null

        try {
            val latestNews = service.getNewsDetails().itemsDetails
                ?.map { newsItemDetailsMapper.fromJsonToRoomDB(it) }
            itemDetails = latestNews?.find { it.itemId == itemID }

            if (itemDetails != null) {
                newsItemDatabase.itemsDetailsDao().insertOneItemDetails(itemDetails)
            } else {
                // TODO make some snackbar with error "Can't find out item with such an id"
            }
        } catch (e: Throwable) {
            // TODO make some snackbar with error "There is empty item list received from json API"
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
