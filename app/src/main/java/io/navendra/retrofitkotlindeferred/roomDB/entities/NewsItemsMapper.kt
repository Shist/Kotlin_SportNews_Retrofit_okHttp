package io.navendra.retrofitkotlindeferred.roomDB.entities

import io.navendra.retrofitkotlindeferred.model.NewsItem

object NewsItemsMapper {

    fun fromJsonToRoomDB(item: NewsItem) : NewsItemDB
    {
        return NewsItemDB(item.id,
            item.body,
            item.featuredMedia.featuredMediaAltText,
            item.featuredMedia.featuredMediaContext.featuredMediaContext,
            item.shortHeadline)
    }

}