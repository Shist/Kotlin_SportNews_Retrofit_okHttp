package io.navendra.retrofitkotlindeferred.ui.repository

import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.model.NewsItemDetails
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsApi
import io.navendra.retrofitkotlindeferred.roomDB.NewsItemDatabase
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem.NewsItemTableImpl
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem.NewsItemMapper
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails.NewsItemDetailsMapper
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails.NewsItemDetailsTableImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepositoryImpl(private val newsItemDatabase: NewsItemDatabase,
                         private val service: SportNewsApi,
                         private val newsItemMapper: NewsItemMapper,
                         private val newsItemDetailsMapper: NewsItemDetailsMapper,
                         private val newsItemTableMapper: NewsItemTableMapper,
                         private val newsItemDetailsTableMapper: NewsItemDetailsTableMapper)
    : NewsRepository {

    private fun isItemWithID(item: NewsItem): Boolean {
        return item.id != null
    }

    private fun isItemWithID(item: NewsItemDetails): Boolean {
        return item.id != null
    }

    private fun isItemNotEmpty(item: NewsItemTableImpl): Boolean {
        return item.altText != null ||
                item.context != null ||
                item.shortHeadline != null
    }

    private fun isItemNotEmpty(item: NewsItemDetailsTableImpl): Boolean {
        return item.body != null ||
                item.context != null ||
                item.shortHeadline != null
    }

    override suspend fun loadNews() {
        val items = service.getNews().items
        val itemsDetails = service.getNewsDetails().itemsDetails

        try {
            newsItemDatabase.itemsDao().insertItemsList(items!!
                .filter { isItemWithID(it) }
                .map { newsItemMapper.fromJsonToRoomDB(it) }
                .filter { isItemNotEmpty(it) })
        } catch (e: Throwable) {
            throw NullPointerException("Error: List<NewsItem> from json is empty!")
        }
        try {
            newsItemDatabase.itemsDetailsDao().insertItemsDetailsList(itemsDetails!!
                .filter { isItemWithID(it) }
                .map { newsItemDetailsMapper.fromJsonToRoomDB(it) }
                .filter { isItemNotEmpty(it) })
        } catch (e: Throwable) {
            throw NullPointerException("Error: List<NewsItemDetails> from json is empty!")
        }
    }

    override suspend fun loadNewsItemDetailsByID(itemID: String) : NewsItemDetailsTable {
        val neededItem: NewsItemDetailsTableImpl?
        val itemsDetails = service.getNewsDetails().itemsDetails

        try {
            val itemsDetailsFiltered = itemsDetails!!
                .filter { isItemWithID(it) }
                .map { newsItemDetailsMapper.fromJsonToRoomDB(it) }
                .filter { isItemNotEmpty(it) }
            newsItemDatabase.itemsDetailsDao().insertItemsDetailsList(itemsDetailsFiltered)
            neededItem = itemsDetailsFiltered.find { it.itemId == itemID }
        } catch (e: Throwable) {
            throw NullPointerException("Error: List<NewsItemDetails> from json is empty!")
        }

        try {
            newsItemDatabase.itemsDetailsDao().insertOneItemDetails(neededItem!!)
        } catch (e: Throwable) {
            throw NullPointerException("Error: It was unable to find out the item with chosen id!")
        }

        return newsItemDetailsTableMapper.fromImplToNotImpl(neededItem)
    }

    override fun getItems(): Flow<List<NewsItemTable>> {
        return newsItemDatabase.itemsDao().getAllItems().map { list ->
            list.map { newsItemTableMapper.fromImplToNotImpl(it) } }
    }

    override fun getItemDetailsByID(itemDetailsId: String): Flow<NewsItemDetailsTable> {
        return newsItemDatabase.itemsDetailsDao().getItemDetailsById(itemDetailsId).map {
            newsItemDetailsTableMapper.fromImplToNotImpl(it) }
    }

}

