package data.roomDB.entities.newsItem

import data.model.NewsItemJson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NewsItemJsonMapper {

    fun fromJsonToRoomDB(itemJson: NewsItemJson) : NewsItemDB
    {
        val featuredMediaAltText: String?
        val featuredMediaContext: String?
        val featuredMediaUri: String?

        if (itemJson.featuredMediaJson == null) {
            featuredMediaAltText = null
            featuredMediaContext = null
            featuredMediaUri = null
        }
        else {
            featuredMediaAltText = itemJson.featuredMediaJson.featuredMediaAltText

            featuredMediaUri = itemJson.uri
            
            featuredMediaContext = if (itemJson.featuredMediaJson.featuredMediaContextJson == null) {
                null
            } else {
                itemJson.featuredMediaJson.featuredMediaContextJson.featuredMediaContext
            }
        }

        return NewsItemDB(itemJson.id!!.substringAfterLast('/'),
            featuredMediaAltText,
            LocalDate.parse(itemJson.createdAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME),
            featuredMediaContext,
            itemJson.shortHeadline,
            featuredMediaUri)
    }

}