package ui.koinModule

import ui.viewModel.NewsListViewModel
import ui.viewModel.NewsPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {

    viewModel { NewsListViewModel(application = get(), newsItemDBMapper = get()) }

    viewModel { params ->
        NewsPageViewModel(
            application = get(),
            itemID = params.get(),
            newsItemDetailsDBMapper = get()
        )
    }

}
