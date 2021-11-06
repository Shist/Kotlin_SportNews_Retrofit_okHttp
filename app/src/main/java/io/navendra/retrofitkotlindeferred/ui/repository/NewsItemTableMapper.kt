package io.navendra.retrofitkotlindeferred.ui.repository

import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem.NewsItemTableImpl

class NewsItemTableMapper {

    fun fromImplToNotImpl(item: NewsItemTableImpl) : NewsItemTable {
        return NewsItemTable(itemId = item.itemId,
            altText = item.altText,
            createdAt = item.createdAt,
            context = item.context,
            shortHeadline = item.shortHeadline)
    }

    fun fromNotImplToImpl(item: NewsItemTable) : NewsItemTableImpl {
        return NewsItemTableImpl(itemId = item.itemId,
            altText = item.altText,
            createdAt = item.createdAt,
            context = item.context,
            shortHeadline = item.shortHeadline)
    }

}