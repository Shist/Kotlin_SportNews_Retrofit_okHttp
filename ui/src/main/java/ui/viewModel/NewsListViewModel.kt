package ui.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import data.roomDB.entities.newsItem.NewsItemDB
import data.repository.LoadState
import data.repository.NewsItemDBMapper
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import domain.NewsRepository
import java.io.IOException

class NewsListViewModel (application: Application,
                         private val newsItemDBMapper: NewsItemDBMapper
                         ) : AndroidViewModel(application), KoinComponent {

    private val newsRepository: NewsRepository by inject()

    val newsListFlow: Flow<List<NewsItemDB>> = newsRepository.getItems().map { list ->
        list.map { newsItemDBMapper.fromNotImplToDB(it) }
    }

    val state: MutableStateFlow<LoadState> = MutableStateFlow(LoadState.IDLE)

    private fun isConnectedToInternet() : Boolean {
        val context = getApplication<Application>().applicationContext
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.Main) {
            state.value = LoadState.LOADING
            try {
                newsRepository.loadNews()
                state.value = LoadState.SUCCESS
            } catch (e: Throwable) {
                if (!isConnectedToInternet() && e is IOException) {
                    state.value = LoadState.INTERNET_ERROR
                }
                else if (e is NullPointerException) {
                    when (e.message) {
                        "Error: List<NewsItem> from json is empty!" ->
                            state.value = LoadState.EMPTY_ITEMS_LIST_ERROR
                        "Error: List<NewsItemDetails> from json is empty!" ->
                            state.value = LoadState.EMPTY_ITEMS_DETAILS_LIST_ERROR
                    }
                }
                else {
                    state.value = LoadState.UNKNOWN_ERROR
                }
            }
        }
    }

}
