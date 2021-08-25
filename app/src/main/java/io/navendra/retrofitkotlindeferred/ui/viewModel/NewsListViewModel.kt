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

    var newsListFlow: Flow<List<NewsItem>> = NewsItemsMapper.flowListFromRoomDBtoJson( NewsRepository.
        getInstance(getApplication<Application>().applicationContext).
        getItems(getApplication<Application>().applicationContext)!! )

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.Main) {
            val context = getApplication<Application>().applicationContext

            NewsRepository.getInstance(context).loadNews()

            newsListFlow = NewsItemsMapper.flowListFromRoomDBtoJson(NewsRepository.
            getInstance(context).getItems(context)!!)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SportViewModel", "SportViewModel destroyed!")
    }

}
