package io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "itemsWithDetails")
data class NewsItemDetailsDB(

    @PrimaryKey
    val itemId: String,

    @ColumnInfo(name = "body")
    val body: String?,

    @ColumnInfo(name = "createdAt")
    val createdAt: LocalDate?,

    @ColumnInfo(name = "context")
    val context: String?,

    @ColumnInfo(name = "shortHeadline")
    val shortHeadline: String?

)
