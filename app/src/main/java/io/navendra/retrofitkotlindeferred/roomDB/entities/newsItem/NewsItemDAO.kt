package io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsItemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneItem(item: NewsItemTableImpl)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSomeItems(vararg nextItem: NewsItemTableImpl)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemsList(items: List<NewsItemTableImpl>)

    @Update
    suspend fun updateOneItem(item: NewsItemTableImpl)

    @Update
    suspend fun updateSomeItems(vararg nextItem: NewsItemTableImpl)

    @Update
    suspend fun updateAllItems(items: List<NewsItemTableImpl>)

    @Delete
    suspend fun deleteOneItem(item: NewsItemTableImpl)

    @Delete
    suspend fun deleteSomeItems(vararg nextItem: NewsItemTableImpl)

    @Delete
    suspend fun deleteAllItems(items: List<NewsItemTableImpl>)

    @Query("SELECT * FROM items ORDER BY createdAt DESC, itemId")
    fun getAllItems(): Flow<List<NewsItemTableImpl>>

    @Query("SELECT * FROM items WHERE itemId = :neededId")
    fun getItemById(neededId: String): Flow<NewsItemTableImpl>

}