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

class NewsPageViewModel (application: Application, item_id: String) : AndroidViewModel(application) {

    var newsPageFlow: Flow<NewsItem> = NewsItemsMapper.flowFromRoomDBtoJson( NewsRepository.
        getInstance(getApplication<Application>().applicationContext).
        getItemByID(getApplication<Application>().applicationContext, item_id) )

    init {
        viewModelScope.launch {

            val context = getApplication<Application>().applicationContext

            NewsRepository.getInstance(context).loadNews()

            newsPageFlow = NewsItemsMapper.flowFromRoomDBtoJson(NewsRepository.
            getInstance(context).getItemByID(context, item_id))

        }
    }

    fun loadData(item_id: String) {
        viewModelScope.launch {
            NewsRepository.getInstance(getApplication<Application>().applicationContext).loadNews()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SportViewModel", "SportViewModel destroyed!")
    }

}
