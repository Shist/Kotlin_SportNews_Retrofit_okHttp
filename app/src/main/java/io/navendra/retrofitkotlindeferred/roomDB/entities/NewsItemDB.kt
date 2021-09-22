package io.navendra.retrofitkotlindeferred.roomDB.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "items")
data class NewsItemDB(

    @PrimaryKey
    val itemId: String,

    @ColumnInfo(name = "body")
    val body: String,

    @ColumnInfo(name = "altText")
    val altText: String,

    @ColumnInfo(name = "createdAt")
    val createdAt: LocalDate,

    @ColumnInfo(name = "context")
    val context: String,

    @ColumnInfo(name = "shortHeadLine")
    var shortHeadline: String

)
