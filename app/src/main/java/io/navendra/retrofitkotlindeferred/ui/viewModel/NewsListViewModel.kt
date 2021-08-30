package io.navendra.retrofitkotlindeferred.ui.viewModel

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import io.navendra.retrofitkotlindeferred.roomDB.entities.NewsItemDB
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class NewsListViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: NewsRepository
        get() = NewsRepository.getInstance(getApplication<Application>().applicationContext)

    var newsListFlow: Flow<List<NewsItemDB>> = repository.getItems()

    private val _state: StateFlow<LoadState> = MutableStateFlow(LoadState.IDLE)
    val state: StateFlow<LoadState> = _state

    var isLoaded: Boolean = false

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadData() {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = LoadState.LOADING
            repository.loadNews()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SportViewModel", "SportViewModel destroyed!")
    }

}
