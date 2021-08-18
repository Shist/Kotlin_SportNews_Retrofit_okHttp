package io.navendra.retrofitkotlindeferred.roomDB.entities

import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.model.NewsItemFeaturedMedia
import io.navendra.retrofitkotlindeferred.model.NewsItemFeaturedMediaContext

class NewsItemsMapper {

    fun fromJsonToRoomDB(item: NewsItem) : NewsItemsDB
    {
        return NewsItemsDB(item.id,
            item.body,
            item.featuredMedia.featuredMediaAltText,
            item.featuredMedia.featuredMediaContext.featuredMediaContext,
            item.shortHeadline)
    }

    fun fromRoomDBtoJson(item: NewsItemsDB) : NewsItem
    {
        return NewsItem(item.itemId,
        item.body,
        NewsItemFeaturedMedia(item.altText, NewsItemFeaturedMediaContext(item.context)),
        item.shortHeadline)
    }

}