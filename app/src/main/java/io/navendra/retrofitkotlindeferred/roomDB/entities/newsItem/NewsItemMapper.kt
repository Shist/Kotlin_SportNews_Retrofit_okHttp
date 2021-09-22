package io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem

import io.navendra.retrofitkotlindeferred.model.NewsItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object NewsItemMapper {

    fun fromJsonToRoomDB(item: NewsItem) : NewsItemTable
    {
        return NewsItemTable(item.id,
            item.featuredMedia.featuredMediaAltText,
            LocalDate.parse(item.createdAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME),
            item.featuredMedia.featuredMediaContext.featuredMediaContext,
            item.shortHeadline)
    }

}