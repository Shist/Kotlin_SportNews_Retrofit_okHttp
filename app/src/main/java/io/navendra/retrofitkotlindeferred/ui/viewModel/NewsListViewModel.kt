package io.navendra.retrofitkotlindeferred.ui.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.navendra.retrofitkotlindeferred.roomDB.entities.newsItem.NewsItemTable
import io.navendra.retrofitkotlindeferred.ui.repository.LoadState
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(application: Application,
                                            private val repository: NewsRepository) :
    AndroidViewModel(application) {

    val newsListFlow: Flow<List<NewsItemTable>> = repository.getItems()

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
                repository.loadNews()
                state.value = LoadState.SUCCESS
            } catch (e: Throwable) {
                if (!isConnectedToInternet() && e is IOException) {
                    state.value = LoadState.INTERNET_ERROR
                }
                else {
                    state.value = LoadState.UNKNOWN_ERROR
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

}
