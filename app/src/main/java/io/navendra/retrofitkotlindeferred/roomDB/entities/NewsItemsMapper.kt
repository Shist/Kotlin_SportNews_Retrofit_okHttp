package io.navendra.retrofitkotlindeferred.roomDB.entities

import io.navendra.retrofitkotlindeferred.model.NewsItem

class NewsItemsMapper {

    fun fromJsonToRoomDB(item: NewsItem) : NewsItemsDB
    {
        return NewsItemsDB(item.id,
            item.body,
            item.featuredMedia.featuredMediaAltText,
            item.featuredMedia.featuredMediaContext.featuredMediaContext,
            item.shortHeadline)
    }

}