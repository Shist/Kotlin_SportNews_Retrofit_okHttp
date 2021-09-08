package io.navendra.retrofitkotlindeferred.roomDB.entities

import io.navendra.retrofitkotlindeferred.model.NewsItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object NewsItemsMapper {

    fun fromJsonToRoomDB(item: NewsItem) : NewsItemDB
    {
        return NewsItemDB(item.id,
            item.body,
            item.featuredMedia.featuredMediaAltText,
            LocalDate.parse(item.createdAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME),
            item.featuredMedia.featuredMediaContext.featuredMediaContext,
            item.shortHeadline)
    }

}