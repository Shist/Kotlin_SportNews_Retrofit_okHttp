package io.navendra.retrofitkotlindeferred.koinModules

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import io.navendra.retrofitkotlindeferred.retrofit.RetrofitClient
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsClient
import io.navendra.retrofitkotlindeferred.roomDB.MigrationDB
import io.navendra.retrofitkotlindeferred.roomDB.NewsItemDatabase
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import io.navendra.retrofitkotlindeferred.ui.viewModel.NewsListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
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

val retrofitClientModule = module {
    single { RetrofitClient(client = get()) }

    single {
        return@single OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }
}

val newsListFragmentModule = module {
    viewModel { NewsListViewModel(application = get()) }
}
