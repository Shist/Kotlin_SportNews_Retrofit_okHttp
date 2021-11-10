package io.navendra.retrofitkotlindeferred.koinModule

import io.navendra.retrofitkotlindeferred.ui.viewModel.NewsListViewModel
import io.navendra.retrofitkotlindeferred.ui.viewModel.NewsPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { NewsListViewModel(application = get(), newsItemDBMapper = get()) }

    viewModel { params ->
        NewsPageViewModel(
            application = get(),
            itemID = params.get(),
            newsItemDetailsDBMapper = get()
        )
    }

}
