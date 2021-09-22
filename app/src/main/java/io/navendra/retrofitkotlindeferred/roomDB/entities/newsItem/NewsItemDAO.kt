package io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsItemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneItem(item: NewsItemTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSomeItems(vararg nextItem: NewsItemTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemsList(items: List<NewsItemTable>)

    @Update
    suspend fun updateOneItem(item: NewsItemTable)

    @Update
    suspend fun updateSomeItems(vararg nextItem: NewsItemTable)

    @Update
    suspend fun updateAllItems(items: List<NewsItemTable>)

    @Delete
    suspend fun deleteOneItem(item: NewsItemTable)

    @Delete
    suspend fun deleteSomeItems(vararg nextItem: NewsItemTable)

    @Delete
    suspend fun deleteAllItems(items: List<NewsItemTable>)

    @Query("SELECT * FROM items ORDER BY createdAt DESC, itemId")
    fun getAllItems(): Flow<List<NewsItemTable>>

    @Query("SELECT * FROM items WHERE itemId = :neededId")
    fun getItemById(neededId: String): Flow<NewsItemTable>

}