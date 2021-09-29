package io.navendra.retrofitkotlindeferred.koinModules

import android.content.Context
import android.view.LayoutInflater
import androidx.room.Room
import io.navendra.retrofitkotlindeferred.databinding.ActivityMainBinding
import io.navendra.retrofitkotlindeferred.retrofit.RetrofitClient
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsClient
import io.navendra.retrofitkotlindeferred.roomDB.MigrationDB
import io.navendra.retrofitkotlindeferred.roomDB.NewsItemDatabase
import io.navendra.retrofitkotlindeferred.ui.repository.LoadState
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import io.navendra.retrofitkotlindeferred.ui.viewModel.NewsListViewModel
import io.navendra.retrofitkotlindeferred.ui.viewModel.NewsPageViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.koin.dsl.module

val newsRepositoryModule = module {
    single { NewsRepository(newsItemDatabase = get(), service = get()) }

    single {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                NewsItemDatabase::class.java, "newsDB")
                .addMigrations(MigrationDB.MIGRATION_1_2)
                .build()

        buildDatabase(androidContext())
    }

    single {
        SportNewsClient.SPORT_NEWS_API
    }

    single (named("newsListFlow")) {
        val newsRepository: NewsRepository by inject()
        newsRepository.getItems()
    }

    single (named("newsPageFlow")) { params ->
        val newsRepository: NewsRepository by inject()
        newsRepository.getItemDetailsByID(itemDetailsId = params.get())
    }

    single { MutableStateFlow(LoadState.IDLE) }
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

val uiModule = module {
    viewModel { NewsListViewModel(application = get()) }

    viewModel { params ->
        NewsPageViewModel(application = get(), itemID = params.get())
    }

    factory { params ->
        ActivityMainBinding.inflate(params.get())
    }
}
