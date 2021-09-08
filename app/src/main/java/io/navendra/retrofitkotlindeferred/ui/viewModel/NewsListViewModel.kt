package io.navendra.retrofitkotlindeferred.ui.viewModel

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import io.navendra.retrofitkotlindeferred.roomDB.entities.NewsItemDB
import io.navendra.retrofitkotlindeferred.ui.repository.LoadState
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class NewsListViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: NewsRepository
        get() = NewsRepository.getInstance(getApplication<Application>().applicationContext)

    val newsListFlow: Flow<List<NewsItemDB>> = repository.getItems()

    val state: MutableStateFlow<LoadState> = MutableStateFlow(LoadState.IDLE)

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadData() {
        viewModelScope.launch(Dispatchers.Main) {
            state.value = LoadState.LOADING
            try {
                repository.loadNews()
                state.value = LoadState.SUCCESS
            } catch (e: Throwable) {
                var listItems = repository.
                if (listItems.isEmpty())
                { // Когда вообще нету данных (даже в базе)
                    state.value = LoadState.ERROR_NO_DATA
                }
                else
                { // Когда новые данные не загрузились, но в базе есть старые
                    state.value = LoadState.ERROR_WITH_DATA
                }
                Log.d("MyLog", "Error while loading data: $e")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SportViewModel", "SportViewModel destroyed!")
    }

}
