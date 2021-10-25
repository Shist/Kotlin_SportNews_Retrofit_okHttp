package io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem

import io.navendra.retrofitkotlindeferred.model.NewsItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NewsItemMapper {

    fun fromJsonToRoomDB(item: NewsItem) : NewsItemTable
    {
        val featuredMediaAltText: String?
        val featuredMediaContext: String?

        if (item.featuredMedia == null) {
            featuredMediaAltText = null
            featuredMediaContext = null
        }
        else {
            featuredMediaAltText = item.featuredMedia.featuredMediaAltText
            
            featuredMediaContext = if (item.featuredMedia.featuredMediaContext == null) {
                null
            } else {
                item.featuredMedia.featuredMediaContext.featuredMediaContext
            }
        }

        return NewsItemTable(item.id!!,
            featuredMediaAltText,
            LocalDate.parse(item.createdAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME),
            featuredMediaContext,
            item.shortHeadline)
    }

}