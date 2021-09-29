package io.navendra.retrofitkotlindeferred.koinModules

import android.content.Context
import androidx.room.Room
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsApi
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsClient
import io.navendra.retrofitkotlindeferred.roomDB.MigrationDB
import io.navendra.retrofitkotlindeferred.roomDB.NewsItemDatabase
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val newsRepositoryModule = module {

    single { NewsRepository(newsItemDatabase = get(), service = get()) }

    single {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                NewsItemDatabase::class.java, "newsDB")
                .addMigrations(MigrationDB.MIGRATION_1_2)
                .build()

        return@single buildDatabase(androidContext())
    }

    single {
        return@single SportNewsClient.SPORT_NEWS_API
    }

}
