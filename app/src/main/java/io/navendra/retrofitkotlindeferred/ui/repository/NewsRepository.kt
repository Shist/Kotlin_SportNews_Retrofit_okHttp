package io.navendra.retrofitkotlindeferred.ui.repository

import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem.NewsItemTable
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails.NewsItemDetailsTable
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun loadNews()

    suspend fun loadNewsItemDetailsByID(itemID: String) : NewsItemDetailsTable

    fun getItems(): Flow<List<NewsItemTable>>

    fun getItemDetailsByID(itemDetailsId: String): Flow<NewsItemDetailsTable>

}