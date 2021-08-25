package io.navendra.retrofitkotlindeferred.roomDB.entities

import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.model.NewsItemFeaturedMedia
import io.navendra.retrofitkotlindeferred.model.NewsItemFeaturedMediaContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

object NewsItemsMapper {

    private fun fromJsonToRoomDB(item: NewsItem) : NewsItemsDB
    {
        return NewsItemsDB(item.id,
            item.body,
            item.featuredMedia.featuredMediaAltText,
            item.featuredMedia.featuredMediaContext.featuredMediaContext,
            item.shortHeadline)
    }

    fun listFromJsonToRoomDB(items: List<NewsItem>) : List<NewsItemsDB>
    {
        val itemsDB: MutableList<NewsItemsDB> = MutableList(items.size) {
            NewsItemsDB("", "", "", "", "") }
        for(i in items.indices) {
            itemsDB[i] = fromJsonToRoomDB(items[i])
        }
        return itemsDB.toList()
    }

    fun flowFromJsonToRoomDB(itemsFlow: Flow<List<NewsItem>>) : Flow<List<NewsItemsDB>>
    {
        return flow {
            itemsFlow.collect {
                val itemsDB: MutableList<NewsItemsDB> = MutableList(it.size) {
                    NewsItemsDB("", "", "", "", "") }
                for (i in it.indices) {
                    itemsDB[i] = fromJsonToRoomDB(it[i])
                    emit(itemsDB.toList())
                }
            }
        }
    }

    fun fromRoomDBtoJson(item: NewsItemsDB) : NewsItem
    {
        return NewsItem(item.itemId,
            item.body,
            NewsItemFeaturedMedia(item.altText, NewsItemFeaturedMediaContext(item.context)),
            item.shortHeadline)
    }

    fun listFromRoomDBtoJson(itemsDB: List<NewsItemsDB>) : List<NewsItem>
    {
        val items: MutableList<NewsItem> = MutableList(itemsDB.size) {
            NewsItem("", "", NewsItemFeaturedMedia("",
                NewsItemFeaturedMediaContext("")), "") }
        for(i in itemsDB.indices) {
            items[i] = fromRoomDBtoJson(itemsDB[i])
        }
        return items.toList()
    }

    fun flowFromRoomDBtoJson(itemsDbFlow: Flow<List<NewsItemsDB>>) : Flow<List<NewsItem>>
    {
        return flow {
            itemsDbFlow.collect {
                val items: MutableList<NewsItem> = MutableList(it.size) {
                    NewsItem("", "", NewsItemFeaturedMedia("",
                        NewsItemFeaturedMediaContext("")), "") }
                for (i in it.indices) {
                    items[i] = fromRoomDBtoJson(it[i])
                    emit(items.toList())
                }
            }
        }
    }

}