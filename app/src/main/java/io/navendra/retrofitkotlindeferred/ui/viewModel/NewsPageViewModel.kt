package io.navendra.retrofitkotlindeferred.ui.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import io.navendra.retrofitkotlindeferred.roomDB.entities.NewsItemDB
import io.navendra.retrofitkotlindeferred.ui.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NewsPageViewModel(application: Application, itemID: String) : AndroidViewModel(application) {

    private val repository: NewsRepository
        get() = NewsRepository.getInstance(getApplication<Application>().applicationContext)

    var newsPageFlow: Flow<NewsItemDB> = repository.getItemByID(itemID)

    fun loadData(itemID: String) {
        viewModelScope.launch {
            repository.loadNewsPageByID(itemID)
            newsPageFlow = repository.getItemByID(itemID)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SportViewModel", "SportViewModel destroyed!")
    }

}
