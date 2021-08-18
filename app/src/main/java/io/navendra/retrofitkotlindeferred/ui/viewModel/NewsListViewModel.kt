package io.navendra.retrofitkotlindeferred.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.navendra.retrofitkotlindeferred.model.newsItem.NewsItem
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class NewsListViewModel : ViewModel() {

    private val _newsListFlow = MutableStateFlow<LatestNewsUiState<List<NewsItem>>>(LatestNewsUiState.Loading)

    val newsListFlow: StateFlow<LatestNewsUiState<List<NewsItem>>> = _newsListFlow

    fun loadData() {
        viewModelScope.launch(Dispatchers.Main) {
            _newsListFlow.value =
                LatestNewsUiState.Success(NewsRepository.loadNews())
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SportViewModel", "SportViewModel destroyed!")
    }

}
