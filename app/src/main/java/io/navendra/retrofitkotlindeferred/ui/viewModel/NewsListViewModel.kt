package io.navendra.retrofitkotlindeferred.ui.viewModel

import android.app.Application
import android.util.Log
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

    fun loadData() {
        viewModelScope.launch(Dispatchers.Main) {
            repository.loadNews()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SportViewModel", "SportViewModel destroyed!")
    }

}
