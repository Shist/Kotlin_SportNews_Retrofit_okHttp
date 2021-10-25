package io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails

import io.navendra.retrofitkotlindeferred.model.NewsItemDetails
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem.NewsItemTable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NewsItemDetailsMapper {

    fun fromJsonToRoomDB(item: NewsItemDetails) : NewsItemDetailsTable
    {
        val featuredMediaContext = if (item.featuredMedia == null) {
            null
        } else {
            if (item.featuredMedia.featuredMediaContext == null) {
                null
            } else {
                item.featuredMedia.featuredMediaContext.featuredMediaContext
            }
        }

        return NewsItemDetailsTable(item.id!!,
            item.body,
            LocalDate.parse(item.createdAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME),
            featuredMediaContext,
            item.shortHeadline)
    }

}