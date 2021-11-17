package data.repository

import data.roomDB.entities.newsItem.NewsItemDB
import domain.NewsItem

class NewsItemDBMapper {

    fun fromDBToNotImpl(item: NewsItemDB) : NewsItem {
        return NewsItem(itemId = item.itemId,
            altText = item.altText,
            createdAt = item.createdAt,
            context = item.context,
            shortHeadline = item.shortHeadline)
    }

    fun fromNotImplToDB(item: NewsItem) : NewsItemDB {
        return NewsItemDB(
            itemId = item.itemId,
            altText = item.altText,
            createdAt = item.createdAt,
            context = item.context,
            shortHeadline = item.shortHeadline
        )
    }

}