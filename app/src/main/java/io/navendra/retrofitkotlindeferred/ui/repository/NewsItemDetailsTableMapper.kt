package io.navendra.retrofitkotlindeferred.ui.repository

import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails.NewsItemDetailsTableImpl

class NewsItemDetailsTableMapper {

    fun fromImplToNotImpl(item: NewsItemDetailsTableImpl) : NewsItemDetailsTable {
        return NewsItemDetailsTable(itemId = item.itemId,
            body = item.body,
            createdAt = item.createdAt,
            context = item.context,
            shortHeadline = item.shortHeadline)
    }

    fun fromNotImplToImpl(item: NewsItemDetailsTable) : NewsItemDetailsTableImpl {
        return NewsItemDetailsTableImpl(itemId = item.itemId,
            body = item.body,
            createdAt = item.createdAt,
            context = item.context,
            shortHeadline = item.shortHeadline)
    }

}