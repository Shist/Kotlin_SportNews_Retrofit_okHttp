package io.navendra.retrofitkotlindeferred.daggerModules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import io.navendra.retrofitkotlindeferred.retrofit.RetrofitClient
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsApi
import io.navendra.retrofitkotlindeferred.roomDB.MigrationDB
import io.navendra.retrofitkotlindeferred.roomDB.NewsItemDatabase
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class DaggerHiltModule {

    @Provides
    @Singleton
    fun provideNewsItemDatabase(context: Context): NewsItemDatabase
    {
        return buildDatabase(context)
    }

    private fun buildDatabase(context: Context) =
        Room.databaseBuilder(context.applicationContext,
            NewsItemDatabase::class.java, "newsDB")
            .addMigrations(MigrationDB.MIGRATION_1_2)
            .build()

    @Provides
    @Singleton
    fun provideRetrofitClient(): RetrofitClient

    @Provides
    @Singleton
    fun provideSportNewsApi() {
        val SPORT_NEWS_API : SportNewsApi by lazy {
            RetrofitClient.retrofit("https://api.beinsports.com/")
                .create(SportNewsApi::class.java)
        }

        val retrofitClient: RetrofitClient = get()

        return retrofitClient.retrofit("https://api.beinsports.com/")
            .create(SportNewsApi::class.java)
    }

}