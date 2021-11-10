package data.roomDB.entities.newsItemDetails

import data.model.NewsItemDetailsJson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NewsItemDetailsJsonMapper {

    fun fromJsonToRoomDB(itemJson: NewsItemDetailsJson) : NewsItemDetailsDB
    {
        val featuredMediaContext = if (itemJson.featuredMediaJson == null) {
            null
        } else {
            if (itemJson.featuredMediaJson.featuredMediaContextJson == null) {
                null
            } else {
                itemJson.featuredMediaJson.featuredMediaContextJson.featuredMediaContext
            }
        }

        return NewsItemDetailsDB(itemJson.id!!,
            itemJson.body,
            LocalDate.parse(itemJson.createdAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME),
            featuredMediaContext,
            itemJson.shortHeadline)
    }

}