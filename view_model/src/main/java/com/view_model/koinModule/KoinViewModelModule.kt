package com.view_model.koinModule

import com.view_model.NewsListViewModel
import com.view_model.NewsPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { NewsListViewModel(application = get()) }

    viewModel { NewsPageViewModel(application = get()) }

}
