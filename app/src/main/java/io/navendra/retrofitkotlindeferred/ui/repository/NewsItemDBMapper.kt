package io.navendra.retrofitkotlindeferred.ui.repository

import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem.NewsItemDB

class NewsItemDBMapper {

    fun fromDBToNotImpl(item: NewsItemDB) : NewsItem {
        return NewsItem(itemId = item.itemId,
            altText = item.altText,
            createdAt = item.createdAt,
            context = item.context,
            shortHeadline = item.shortHeadline)
    }

    fun fromNotImplToDB(item: NewsItem) : NewsItemDB {
        return NewsItemDB(itemId = item.itemId,
            altText = item.altText,
            createdAt = item.createdAt,
            context = item.context,
            shortHeadline = item.shortHeadline)
    }

}