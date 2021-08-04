package io.navendra.retrofitkotlindeferred.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsClient
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

class NewsListViewModel : ViewModel() {

    var news: List<NewsItem> = emptyList()

    private lateinit var newsFlow: MutableStateFlow<List<NewsItem>>

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow(LatestNewsUiState.Success(emptyList()))

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<LatestNewsUiState> = _uiState

    fun loadData(){

        val service = SportNewsClient.SPORT_NEWS_API

        viewModelScope.launch(Dispatchers.Main) {
            try {
                val userRequest = service.getNews()
                newsFlow.collect { data ->
                    news = data // Достаем и сохраняем данные в news
                    _uiState.value = LatestNewsUiState.Success(data) // Записываем, успешно или нет всё прошло
                }
                if(userRequest.items.isNotEmpty()){
                    Log.d("MyLog", "Successful start logging...")
                    Log.d("MyLog", "response: ${userRequest.items.size} items")
                    for (i in userRequest.items.indices)
                        Log.d("MyLog", userRequest.items[i].toString())
                }else{
                    Log.d("MyLog", "Failure while getting response...")
                }
            } catch (e: Throwable){
                Log.d("MyLog", "Failure...", e)
            }
        }
    }

    fun getItemByID(itemID: String?): NewsItem? {
        return news.find { it.id == itemID }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SportViewModel", "SportViewModel destroyed!")
    }

}

// Represents different states for the news screen
sealed class LatestNewsUiState {
    data class Success(val news: List<NewsItem>): LatestNewsUiState()
    data class Error(val exception: Throwable): LatestNewsUiState() {
        fun showError(exception: Throwable) {
            Log.d("MyLog", "Failure: ", exception)
        }
    }
}
