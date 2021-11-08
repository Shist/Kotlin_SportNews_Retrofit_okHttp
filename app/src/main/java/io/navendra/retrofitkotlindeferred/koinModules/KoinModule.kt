package io.navendra.retrofitkotlindeferred.koinModules

import android.content.Context
import androidx.room.Room
import io.navendra.retrofitkotlindeferred.retrofit.RetrofitClient
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsApi
import io.navendra.retrofitkotlindeferred.roomDB.MigrationDB
import io.navendra.retrofitkotlindeferred.roomDB.NewsItemDatabase
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem.NewsItemJsonMapper
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails.NewsItemDetailsJsonMapper
import io.navendra.retrofitkotlindeferred.ui.repository.NewsItemDetailsDBMapper
import io.navendra.retrofitkotlindeferred.ui.repository.NewsItemDBMapper
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepositoryImpl
import io.navendra.retrofitkotlindeferred.ui.viewModel.NewsListViewModel
import io.navendra.retrofitkotlindeferred.ui.viewModel.NewsPageViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val newsRepositoryModule = module {
    single<NewsRepository> { NewsRepositoryImpl(newsItemDatabase = get(), service = get(),
    newsItemJsonMapper = get(), newsItemDetailsJsonMapper = get(), newsItemDBMapper = get(),
    newsItemDetailsDBMapper = get()) }

    single {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                NewsItemDatabase::class.java, "newsDB")
                .addMigrations(MigrationDB.MIGRATION_2_3)
                .build()

        buildDatabase(androidContext())
    }

    single {
        val retrofitClient: RetrofitClient = get()

        retrofitClient.retrofit("https://api.beinsports.com/")
            .create(SportNewsApi::class.java)
    }
}

val retrofitClientModule = module {
    single { RetrofitClient(client = get()) }

    single {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }
}

val roomDbModule = module {
    single { NewsItemJsonMapper() }

    single { NewsItemDetailsJsonMapper() }
}

val uiModule = module {
    viewModel { NewsListViewModel(application = get(), newsItemDBMapper = get()) }

    single { NewsItemDBMapper() }

    viewModel { params ->
        NewsPageViewModel(application = get(), itemID = params.get(), newsItemDetailsDBMapper = get())
    }

    single { NewsItemDetailsDBMapper() }
}
