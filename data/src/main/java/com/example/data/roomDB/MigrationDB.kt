package com.example.data.roomDB

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object MigrationDB {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("CREATE TABLE IF NOT EXISTS itemsWithDetails (" +
                    "itemId TEXT PRIMARY KEY NOT NULL, " +
                    "body TEXT NOT NULL, " +
                    "createdAt INTEGER NOT NULL, " +
                    "context TEXT NOT NULL, " +
                    "shortHeadline TEXT NOT NULL);")
            db.execSQL("INSERT INTO " +
                    "itemsWithDetails(itemId, body, createdAt, context, shortHeadline) " +
                    "SELECT itemId, body, createdAt, context, shortHeadLine FROM items;")
            db.execSQL("CREATE TABLE IF NOT EXISTS itemsTemp (" +
                    "itemId TEXT PRIMARY KEY NOT NULL, " +
                    "altText TEXT NOT NULL, " +
                    "createdAt INTEGER NOT NULL, " +
                    "context TEXT NOT NULL, " +
                    "shortHeadline TEXT NOT NULL);")
            db.execSQL("INSERT INTO " +
                    "itemsTemp(itemId, altText, createdAt, context, shortHeadline) " +
                    "SELECT itemId, altText, createdAt, context, shortHeadLine FROM items;")
            db.execSQL("DROP TABLE IF EXISTS items;")
            db.execSQL("CREATE TABLE IF NOT EXISTS items (" +
                    "itemId TEXT PRIMARY KEY NOT NULL, " +
                    "altText TEXT NOT NULL, " +
                    "createdAt INTEGER NOT NULL, " +
                    "context TEXT NOT NULL, " +
                    "shortHeadline TEXT NOT NULL);")
            db.execSQL("INSERT INTO items SELECT * FROM itemsTemp;")
            db.execSQL("DROP TABLE itemsTemp;")
        }
    }
    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("CREATE TABLE IF NOT EXISTS itemsTemp (" +
                    "itemId TEXT PRIMARY KEY NOT NULL, " +
                    "altText TEXT, " +
                    "createdAt INTEGER, " +
                    "context TEXT, " +
                    "shortHeadline TEXT);")
            db.execSQL("INSERT INTO " +
                    "itemsTemp(itemId, altText, createdAt, context, shortHeadline) " +
                    "SELECT itemId, altText, createdAt, context, shortHeadLine FROM items;")
            db.execSQL("DROP TABLE IF EXISTS items;")
            db.execSQL("CREATE TABLE IF NOT EXISTS items (" +
                    "itemId TEXT PRIMARY KEY NOT NULL, " +
                    "altText TEXT, " +
                    "createdAt INTEGER, " +
                    "context TEXT, " +
                    "shortHeadline TEXT);")
            db.execSQL("INSERT INTO items SELECT * FROM itemsTemp;")
            db.execSQL("DROP TABLE itemsTemp;")
            db.execSQL("CREATE TABLE IF NOT EXISTS itemsWithDetailsTemp (" +
                    "itemId TEXT PRIMARY KEY NOT NULL, " +
                    "body TEXT, " +
                    "createdAt INTEGER, " +
                    "context TEXT, " +
                    "shortHeadline TEXT);")
            db.execSQL("INSERT INTO " +
                    "itemsWithDetailsTemp(itemId, body, createdAt, context, shortHeadline) " +
                    "SELECT itemId, body, createdAt, context, shortHeadLine FROM itemsWithDetails;")
            db.execSQL("DROP TABLE IF EXISTS itemsWithDetails;")
            db.execSQL("CREATE TABLE IF NOT EXISTS itemsWithDetails (" +
                    "itemId TEXT PRIMARY KEY NOT NULL, " +
                    "body TEXT, " +
                    "createdAt INTEGER, " +
                    "context TEXT, " +
                    "shortHeadline TEXT);")
            db.execSQL("INSERT INTO itemsWithDetails SELECT * FROM itemsWithDetailsTemp;")
            db.execSQL("DROP TABLE itemsWithDetailsTemp;")
        }
    }
}