package io.navendra.retrofitkotlindeferred.ui.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.roomDB.entities.NewsItemsMapper
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class NewsListViewModel (application: Application) : AndroidViewModel(application) {

    // Уберём эти Flow, когда сделаем Flow из БД
    private val _newsListFlow = MutableStateFlow<LatestNewsUiState<List<NewsItem>>>(LatestNewsUiState.Loading)

    val newsListFlow: StateFlow<LatestNewsUiState<List<NewsItem>>> = _newsListFlow

    init {
        viewModelScope.launch(Dispatchers.Main) {
            NewsRepository.newsDatabase?.itemsDao()?.getAllItems()?.collect {
                _newsListFlow.value =
                    LatestNewsUiState.Success(NewsItemsMapper.listFromRoomDBtoJson(it))
            }
        }
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.Main) {
            NewsRepository.getInstance(getApplication<Application>().applicationContext).loadNews()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SportViewModel", "SportViewModel destroyed!")
    }

}
