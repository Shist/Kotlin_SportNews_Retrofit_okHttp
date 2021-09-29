package io.navendra.retrofitkotlindeferred.ui.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails.NewsItemDetailsTable
import io.navendra.retrofitkotlindeferred.ui.repository.LoadState
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.IOException

class NewsPageViewModel(application: Application, itemID: String) :
    AndroidViewModel(application), KoinComponent {

    private val newsRepository by inject<NewsRepository>()

    val newsPageFlow: Flow<NewsItemDetailsTable> = newsRepository.getItemDetailsByID(itemID)

    fun loadData(itemID: String) {
        viewModelScope.launch {
            try {
                newsRepository.loadNewsItemDetailsByID(itemID)
            } catch (e: Throwable) {
//                if (!isConnectedToInternet() && e is IOException) {
//                    state.value = LoadState.INTERNET_ERROR
//                }
//                else {
//                    state.value = LoadState.UNKNOWN_ERROR
//                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SportViewModel", "SportViewModel destroyed!")
    }

}
