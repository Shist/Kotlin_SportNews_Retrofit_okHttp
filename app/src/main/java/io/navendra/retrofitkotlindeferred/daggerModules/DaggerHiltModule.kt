package io.navendra.retrofitkotlindeferred.daggerModules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.navendra.retrofitkotlindeferred.retrofit.RetrofitClient
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsApi
import io.navendra.retrofitkotlindeferred.roomDB.MigrationDB
import io.navendra.retrofitkotlindeferred.roomDB.NewsItemDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaggerHiltModule {

    @Provides
    @Singleton
    fun provideNewsItemDatabase(@ApplicationContext context: Context): NewsItemDatabase {
        return buildDatabase(context)
    }

    private fun buildDatabase(context: Context) =
        Room.databaseBuilder(context.applicationContext,
            NewsItemDatabase::class.java, "newsDB")
            .addMigrations(MigrationDB.MIGRATION_1_2)
            .build()

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(client: OkHttpClient): RetrofitClient {
        return RetrofitClient(client)
    }

    @Provides
    @Singleton
    fun provideSportNewsApi(retrofitClient: RetrofitClient): SportNewsApi {
        return retrofitClient.retrofit("https://api.beinsports.com/")
            .create(SportNewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSportNewsApi(retrofitClient: RetrofitClient): SportNewsApi {
        return retrofitClient.retrofit("https://api.beinsports.com/")
            .create(SportNewsApi::class.java)
    }

}