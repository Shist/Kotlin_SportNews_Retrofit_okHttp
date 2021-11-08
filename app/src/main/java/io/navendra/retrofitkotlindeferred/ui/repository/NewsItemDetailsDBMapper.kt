package io.navendra.retrofitkotlindeferred.ui.repository

import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails.NewsItemDetailsDB

class NewsItemDetailsDBMapper {

    fun fromDBToNotImpl(item: NewsItemDetailsDB) : NewsItemDetails {
        return NewsItemDetails(itemId = item.itemId,
            body = item.body,
            createdAt = item.createdAt,
            context = item.context,
            shortHeadline = item.shortHeadline)
    }

    fun fromDBToImpl(item: NewsItemDetails) : NewsItemDetailsDB {
        return NewsItemDetailsDB(itemId = item.itemId,
            body = item.body,
            createdAt = item.createdAt,
            context = item.context,
            shortHeadline = item.shortHeadline)
    }

}