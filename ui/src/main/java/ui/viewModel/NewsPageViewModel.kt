package ui.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import domain.NewsItemDetails
import domain.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.IOException

class NewsPageViewModel(application: Application,
                        itemID: String)
    : AndroidViewModel(application), KoinComponent {

    private val newsRepository: NewsRepository by inject()

    val newsPageFlow: Flow<NewsItemDetails> = newsRepository.getItemDetailsByID(itemID)

    val state: MutableStateFlow<LoadState> = MutableStateFlow(LoadState.IDLE)

    private fun isConnectedToInternet() : Boolean {
        val context = getApplication<Application>().applicationContext
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }

    fun loadData(itemID: String) {
        viewModelScope.launch {
            state.value = LoadState.LOADING
            try {
                newsRepository.loadNewsItemDetailsByID(itemID)
                state.value = LoadState.SUCCESS
            } catch (e: Throwable) {
                if (!isConnectedToInternet() && e is IOException) {
                    state.value = LoadState.INTERNET_ERROR
                }
                else if (e is NullPointerException) {
                    when (e.message) {
                        "Error: List<NewsItemDetails> from json is empty!" ->
                            state.value = LoadState.EMPTY_ITEMS_DETAILS_LIST_ERROR
                        "Error: It was unable to find out the item with chosen id!" ->
                            state.value = LoadState.INCONSISTENCY_ITEM_ID_ERROR
                    }
                }
                else {
                    state.value = LoadState.UNKNOWN_ERROR
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SportViewModel", "SportViewModel destroyed!")
    }

}
