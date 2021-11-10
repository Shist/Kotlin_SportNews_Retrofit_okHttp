package data.koinModule

import android.content.Context
import androidx.room.Room
import data.retrofit.RetrofitClient
import data.retrofit.SportNewsApi
import data.roomDB.MigrationDB
import data.roomDB.NewsItemDatabase
import data.roomDB.entities.newsItem.NewsItemJsonMapper
import data.roomDB.entities.newsItemDetails.NewsItemDetailsJsonMapper
import data.repository.NewsItemDBMapper
import data.repository.NewsItemDetailsDBMapper
import data.repository.NewsRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import domain.NewsRepository

val dataModule = module {

    single<NewsRepository> {
        NewsRepositoryImpl(
            newsItemDatabase = get(), service = get(),
            newsItemJsonMapper = get(), newsItemDetailsJsonMapper = get(), newsItemDBMapper = get(),
            newsItemDetailsDBMapper = get()
        )
    }

    single {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsItemDatabase::class.java, "newsDB"
            )
                .addMigrations(MigrationDB.MIGRATION_2_3)
                .build()

        buildDatabase(androidContext())
    }

    single {
        val retrofitClient: RetrofitClient = get()

        retrofitClient.retrofit("https://api.beinsports.com/")
            .create(SportNewsApi::class.java)
    }

    single { NewsItemJsonMapper() }

    single { NewsItemDetailsJsonMapper() }

    single { NewsItemDBMapper() }

    single { NewsItemDetailsDBMapper() }

    single { RetrofitClient(client = get()) }

    single {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

}