package io.navendra.retrofitkotlindeferred.roomDB.entities

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsItemsDAO {

    @Insert
    suspend fun insertOneItem(item: NewsItemsDB)

    @Insert
    suspend fun insertSomeItems(vararg nextItem: NewsItemsDB)

    @Insert
    suspend fun insertItemsList(items: List<NewsItemsDB>)

    @Update
    suspend fun updateOneItem(item: NewsItemsDB)

    @Update
    suspend fun updateSomeItems(vararg nextItem: NewsItemsDB)

    @Update
    suspend fun updateAllItems(items: List<NewsItemsDB>)

    @Delete
    suspend fun deleteOneItem(item: NewsItemsDB)

    @Delete
    suspend fun deleteSomeItems(vararg nextItem: NewsItemsDB)

    @Delete
    suspend fun deleteAllItems(items: List<NewsItemsDB>)

    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<NewsItemsDB>>

    @Query("SELECT * FROM items WHERE itemId = :neededId")
    fun getItemById(neededId: String): Flow<NewsItemsDB?>

}