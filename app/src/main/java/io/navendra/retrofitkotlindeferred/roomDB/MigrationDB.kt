package io.navendra.retrofitkotlindeferred.roomDB

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object MigrationDB {
    internal val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("CREATE TABLE IF NOT EXISTS itemsWithDetails (" +
                    "itemId TEXT PRIMARY KEY NOT NULL, " +
                    "body TEXT NOT NULL, " +
                    "createdAt INTEGER NOT NULL, " +
                    "context TEXT NOT NULL, " +
                    "shortHeadLine TEXT NOT NULL);")
            db.execSQL("INSERT INTO " +
                    "itemsWithDetails(itemId, body, createdAt, context, shortHeadLine) " +
                    "SELECT itemId, body, createdAt, context, shortHeadLine FROM items;")
            db.execSQL("CREATE TABLE IF NOT EXISTS itemsTemp (" +
                    "itemId TEXT PRIMARY KEY NOT NULL, " +
                    "altText TEXT NOT NULL, " +
                    "createdAt INTEGER NOT NULL, " +
                    "context TEXT NOT NULL, " +
                    "shortHeadLine TEXT NOT NULL);")
            db.execSQL("INSERT INTO " +
                    "itemsTemp(itemId, altText, createdAt, context, shortHeadLine) " +
                    "SELECT itemId, altText, createdAt, context, shortHeadLine FROM items;")
            db.execSQL("DROP TABLE IF EXISTS items;")
            db.execSQL("CREATE TABLE IF NOT EXISTS items (" +
                    "itemId TEXT PRIMARY KEY NOT NULL, " +
                    "altText TEXT NOT NULL, " +
                    "createdAt INTEGER NOT NULL, " +
                    "context TEXT NOT NULL, " +
                    "shortHeadLine TEXT NOT NULL);")
            db.execSQL("INSERT INTO items SELECT * FROM itemsTemp;")
            db.execSQL("DROP TABLE itemsTemp;")
        }
    }
}