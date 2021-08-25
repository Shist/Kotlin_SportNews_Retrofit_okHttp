package io.navendra.retrofitkotlindeferred.roomDB.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class NewsItemDB(

    @PrimaryKey
    var itemId: String,

    @ColumnInfo(name = "body")
    val body: String,

    @ColumnInfo(name = "altText")
    val altText: String,

    @ColumnInfo(name = "context")
    val context: String,

    @ColumnInfo(name = "shortHeadLine")
    var shortHeadline: String

)
