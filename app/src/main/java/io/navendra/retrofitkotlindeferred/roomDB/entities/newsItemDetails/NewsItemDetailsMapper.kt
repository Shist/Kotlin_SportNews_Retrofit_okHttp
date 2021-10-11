package io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails

import io.navendra.retrofitkotlindeferred.model.NewsItemDetails
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NewsItemDetailsMapper {

    fun fromJsonToRoomDB(item: NewsItemDetails) : NewsItemDetailsTable
    {
        return NewsItemDetailsTable(item.id,
            item.body,
            LocalDate.parse(item.createdAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME),
            item.featuredMedia.featuredMediaContext.featuredMediaContext,
            item.shortHeadline)
    }

}