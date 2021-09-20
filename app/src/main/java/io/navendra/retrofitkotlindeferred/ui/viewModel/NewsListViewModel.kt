package io.navendra.retrofitkotlindeferred.ui.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import io.navendra.retrofitkotlindeferred.roomDB.entities.NewsItemDB
import io.navendra.retrofitkotlindeferred.ui.repository.LoadState
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.io.IOException

class NewsListViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: NewsRepository
        get() = NewsRepository.getInstance(getApplication<Application>().applicationContext)

    val newsListFlow: Flow<List<NewsItemDB>> = repository.getItems()

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
