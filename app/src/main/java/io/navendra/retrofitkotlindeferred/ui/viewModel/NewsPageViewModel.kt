package io.navendra.retrofitkotlindeferred.ui.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItemDetails.NewsItemDetailsTable
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NewsPageViewModel(application: Application, itemID: String) : AndroidViewModel(application) {

    private val repository: NewsRepository
        get() = NewsRepository.getInstance(getApplication<Application>().applicationContext)

    val newsPageFlow: Flow<NewsItemDetailsTable> = repository.getItemDetailsByID(itemID)

    fun loadData(itemID: String) {
        viewModelScope.launch {
            try {
                repository.loadNewsItemDetailsByID(itemID)
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
