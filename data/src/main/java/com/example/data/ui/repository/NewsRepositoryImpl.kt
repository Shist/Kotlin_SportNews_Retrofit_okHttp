package com.example.data.ui.repository

import com.example.data.model.NewsItemDetailsJson
import com.example.data.model.NewsItemJson
import com.example.data.roomDB.NewsItemDatabase
import com.example.data.roomDB.entities.newsItem.NewsItemDB
import com.example.data.roomDB.entities.newsItem.NewsItemJsonMapper
import com.example.data.roomDB.entities.newsItemDetails.NewsItemDetailsDB
import com.example.data.roomDB.entities.newsItemDetails.NewsItemDetailsJsonMapper
import com.example.data.retrofit.SportNewsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ui.repository.NewsItem
import ui.repository.NewsItemDetails
import ui.repository.NewsRepository

class NewsRepositoryImpl(private val newsItemDatabase: NewsItemDatabase,
                         private val service: SportNewsApi,
                         private val newsItemJsonMapper: NewsItemJsonMapper,
                         private val newsItemDetailsJsonMapper: NewsItemDetailsJsonMapper,
                         private val newsItemDBMapper: NewsItemDBMapper,
                         private val newsItemDetailsDBMapper: NewsItemDetailsDBMapper
)
    : NewsRepository {

    private fun isItemWithID(itemJson: NewsItemJson): Boolean {
        return itemJson.id != null
    }

    private fun isItemWithID(itemJson: NewsItemDetailsJson): Boolean {
        return itemJson.id != null
    }

    private fun isItemNotEmpty(item: NewsItemDB): Boolean {
        return item.altText != null ||
                item.context != null ||
                item.shortHeadline != null
    }

    private fun isItemNotEmpty(item: NewsItemDetailsDB): Boolean {
        return item.body != null ||
                item.context != null ||
                item.shortHeadline != null
    }

    override suspend fun loadNews() {
        val items = service.getNews().itemJsons
        val itemsDetails = service.getNewsDetails().itemsDetails

        try {
            newsItemDatabase.itemsDao().insertItemsList(items!!
                .filter { isItemWithID(it) }
                .map { newsItemJsonMapper.fromJsonToRoomDB(it) }
                .filter { isItemNotEmpty(it) })
        } catch (e: Throwable) {
            throw NullPointerException("Error: List<NewsItem> from json is empty!")
        }
        try {
            newsItemDatabase.itemsDetailsDao().insertItemsDetailsList(itemsDetails!!
                .filter { isItemWithID(it) }
                .map { newsItemDetailsJsonMapper.fromJsonToRoomDB(it) }
                .filter { isItemNotEmpty(it) })
        } catch (e: Throwable) {
            throw NullPointerException("Error: List<NewsItemDetails> from json is empty!")
        }
    }

    override suspend fun loadNewsItemDetailsByID(itemID: String) : NewsItemDetails {
        val neededItem: NewsItemDetailsDB?
        val itemsDetails = service.getNewsDetails().itemsDetails

        try {
            val itemsDetailsFiltered = itemsDetails!!
                .filter { isItemWithID(it) }
                .map { newsItemDetailsJsonMapper.fromJsonToRoomDB(it) }
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

        return newsItemDetailsDBMapper.fromDBToNotImpl(neededItem)
    }

    override fun getItems(): Flow<List<NewsItem>> {
        return newsItemDatabase.itemsDao().getAllItems().map { list ->
            list.map { newsItemDBMapper.fromDBToNotImpl(it) } }
    }

    override fun getItemDetailsByID(itemDetailsId: String): Flow<NewsItemDetails> {
        return newsItemDatabase.itemsDetailsDao().getItemDetailsById(itemDetailsId).map {
            newsItemDetailsDBMapper.fromDBToNotImpl(it) }
    }

}

