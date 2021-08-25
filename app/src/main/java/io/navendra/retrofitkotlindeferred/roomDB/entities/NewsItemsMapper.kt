package io.navendra.retrofitkotlindeferred.roomDB.entities

import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.model.NewsItemFeaturedMedia
import io.navendra.retrofitkotlindeferred.model.NewsItemFeaturedMediaContext

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

}