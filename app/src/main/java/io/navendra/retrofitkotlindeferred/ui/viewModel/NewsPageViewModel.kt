package io.navendra.retrofitkotlindeferred.ui.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.roomDB.entities.NewsItemsMapper
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

class NewsPageViewModel(application: Application, item_id: String) : AndroidViewModel(application) {
    private val repository: NewsRepository
        get() = NewsRepository.getInstance(getApplication<Application>().applicationContext)

    var newsPageFlow: Flow<NewsItem> = repository.getItemByID(item_id).mapNotNull {
        it ?: return@mapNotNull null
        NewsItemsMapper.fromRoomDBtoJson(it)
    }


    init {
        loadData(item_id)
    }

    fun loadData(item_id: String) {
        viewModelScope.launch {
            repository.loadNews()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SportViewModel", "SportViewModel destroyed!")
    }

}
