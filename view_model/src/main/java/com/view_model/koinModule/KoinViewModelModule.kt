package com.view_model.koinModule

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { com.view_model.NewsListViewModel(application = get()) }

    viewModel { params ->
        com.view_model.NewsPageViewModel(
            application = get(),
            itemID = params.get()
        )
    }

}
