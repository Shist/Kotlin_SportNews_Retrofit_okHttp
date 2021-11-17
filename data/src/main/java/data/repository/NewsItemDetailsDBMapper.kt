package data.repository

import data.roomDB.entities.newsItemDetails.NewsItemDetailsDB
import domain.NewsItemDetails

class NewsItemDetailsDBMapper {

    fun fromDBToNotImpl(item: NewsItemDetailsDB) : NewsItemDetails {
        return NewsItemDetails(itemId = item.itemId,
            body = item.body,
            createdAt = item.createdAt,
            context = item.context,
            shortHeadline = item.shortHeadline)
    }

    fun fromDBToImpl(item: NewsItemDetails) : NewsItemDetailsDB {
        return NewsItemDetailsDB(
            itemId = item.itemId,
            body = item.body,
            createdAt = item.createdAt,
            context = item.context,
            shortHeadline = item.shortHeadline
        )
    }

}