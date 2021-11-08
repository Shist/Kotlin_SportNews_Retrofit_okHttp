package io.navendra.retrofitkotlindeferred.ui.repository

import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem.NewsItemDB

class NewsItemTableMapper {

    fun fromImplToNotImpl(item: NewsItemDB) : NewsItem {
        return NewsItem(itemId = item.itemId,
            altText = item.altText,
            createdAt = item.createdAt,
            context = item.context,
            shortHeadline = item.shortHeadline)
    }

    fun fromNotImplToImpl(item: NewsItem) : NewsItemDB {
        return NewsItemDB(itemId = item.itemId,
            altText = item.altText,
            createdAt = item.createdAt,
            context = item.context,
            shortHeadline = item.shortHeadline)
    }

}