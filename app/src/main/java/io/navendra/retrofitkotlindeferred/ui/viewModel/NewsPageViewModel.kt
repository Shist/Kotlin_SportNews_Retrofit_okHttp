package io.navendra.retrofitkotlindeferred.ui.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.roomDB.entities.NewsItemsMapper
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class NewsPageViewModel (application: Application, item_id: String) : AndroidViewModel(application) {

    // Прокинуть item_id в конструктор (создание модельки через факторку)
    private val _newsPageFlow = MutableStateFlow<LatestNewsUiState<NewsItem>>(LatestNewsUiState.Loading)

    val newsPageFlow: StateFlow<LatestNewsUiState<NewsItem>> = _newsPageFlow.asStateFlow()

    fun loadData(item_id: String) {
        viewModelScope.launch {
            NewsRepository.getInstance(getApplication<Application>().applicationContext).
            newsDatabase?.itemsDao()?.getItemById(item_id)?.collect {
                _newsPageFlow.value =
                    LatestNewsUiState.Success(NewsItemsMapper.fromRoomDBtoJson(it!!))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SportViewModel", "SportViewModel destroyed!")
    }

}
