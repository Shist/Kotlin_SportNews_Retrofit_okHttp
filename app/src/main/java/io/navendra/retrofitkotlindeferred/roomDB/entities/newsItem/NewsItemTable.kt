package io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "items")
data class NewsItemTable(

    @PrimaryKey
    val itemId: String,

    @ColumnInfo(name = "altText")
    val altText: String?,

    @ColumnInfo(name = "createdAt")
    val createdAt: LocalDate?,

    @ColumnInfo(name = "context")
    val context: String?,

    @ColumnInfo(name = "shortHeadline")
    var shortHeadline: String?

)
