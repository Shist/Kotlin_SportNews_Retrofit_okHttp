package io.navendra.retrofitkotlindeferred.roomDB.entities

import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.model.NewsItemFeaturedMedia
import io.navendra.retrofitkotlindeferred.model.NewsItemFeaturedMediaContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

object NewsItemsMapper {

    // Один айтем из Json в RoomDB
    private fun fromJsonToRoomDB(item: NewsItem) : NewsItemsDB
    {
        return NewsItemsDB(item.id,
            item.body,
            item.featuredMedia.featuredMediaAltText,
            item.featuredMedia.featuredMediaContext.featuredMediaContext,
            item.shortHeadline)
    }

    // Лист айтемов из Json в RoomDB
    fun listFromJsonToRoomDB(items: List<NewsItem>) : List<NewsItemsDB>
    {
        val itemsDB: MutableList<NewsItemsDB> = MutableList(items.size) {
            NewsItemsDB("", "", "", "", "") }
        for(i in items.indices) {
            itemsDB[i] = fromJsonToRoomDB(items[i])
        }
        return itemsDB.toList()
    }

    // Flow из одного айтема из Json в RoomDB
    fun flowFromJsonToRoomDB(itemFlow: Flow<NewsItem>) : Flow<NewsItemsDB>
    {
        return flow {
            itemFlow.collect {
                emit(fromJsonToRoomDB(it))
            }
        }
    }

    // Flow из листа айтемов из Json в RoomDB
    fun flowListFromJsonToRoomDB(itemsFlow: Flow<List<NewsItem>>) : Flow<List<NewsItemsDB>>
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

    // Один айтем из RoomDB в Json
    fun fromRoomDBtoJson(item: NewsItemsDB) : NewsItem
    {
        return NewsItem(item.itemId,
            item.body,
            NewsItemFeaturedMedia(item.altText, NewsItemFeaturedMediaContext(item.context)),
            item.shortHeadline)
    }

    // Лист айтемов из RoomDB в Json
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

    // Flow из одного айтема из RoomDB в Json
    fun flowFromRoomDBtoJson(itemDbFlow: Flow<NewsItemsDB?>?) : Flow<NewsItem>
    {
        return flow {
            itemDbFlow?.collect {
                emit(fromRoomDBtoJson(it!!))
            }
        }
    }

    // Flow из листа айтемов из RoomDB в Json
    fun flowListFromRoomDBtoJson(itemsDbFlow: Flow<List<NewsItemsDB>>) : Flow<List<NewsItem>>
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