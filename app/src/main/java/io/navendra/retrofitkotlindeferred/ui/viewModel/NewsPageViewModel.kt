package io.navendra.retrofitkotlindeferred.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class NewsPageViewModel : ViewModel() {

    private val _newsPageFlow = MutableStateFlow<LatestNewsUiState<NewsItem>>(LatestNewsUiState.Loading)

    val newsPageFlow: StateFlow<LatestNewsUiState<NewsItem>> = _newsPageFlow.asStateFlow()

    fun loadData(item_id: String) {
        viewModelScope.launch {
            NewsRepository.loadNews()
            _newsPageFlow.value = LatestNewsUiState.Success(NewsRepository.
            getNewsPageByID(item_id))
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SportViewModel", "SportViewModel destroyed!")
    }

}
