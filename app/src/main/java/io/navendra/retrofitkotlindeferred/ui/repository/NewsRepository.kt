package io.navendra.retrofitkotlindeferred.ui.repository

import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun loadNews()

    // Ещё 2 класса NewsItemTableNotImpl и NewsItemDetailsTableNotImpl (которые скрывают детали реализации бд) и 2 маппера к ним (быстрый перевод из бд-шного класса в обычный и обратно)
    suspend fun loadNewsItemDetailsByID(itemID: String) : NewsItemDetailsTable

    fun getItems(): Flow<List<NewsItemTable>>

    fun getItemDetailsByID(itemDetailsId: String): Flow<NewsItemDetailsTable>

}