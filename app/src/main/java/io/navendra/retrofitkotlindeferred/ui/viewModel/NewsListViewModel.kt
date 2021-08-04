package io.navendra.retrofitkotlindeferred.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsClient
import io.navendra.retrofitkotlindeferred.ui.newsFlow.NewsRemoteDataSource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

class NewsListViewModel(private val newsFlowData: NewsRemoteDataSource) : ViewModel() {

    var news: List<NewsItem> = emptyList()

    private val _newsFlow = MutableStateFlow(LatestNewsUiState.Success(emptyList()))

    val newsFlow: StateFlow<LatestNewsUiState> = _newsFlow

    fun loadData(){

        val service = SportNewsClient.SPORT_NEWS_API

        viewModelScope.launch(Dispatchers.Main) {
            try {
                val userRequest = service.getNews()
                newsFlowData.latestNews.collect { data ->
                    news = data // Достаем и сохраняем данные в news
                    _newsFlow.value =
                        LatestNewsUiState.Success(data) // Записываем, успешно или нет всё прошло
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

sealed class LatestNewsUiState {
    data class Success(val news: List<NewsItem>): LatestNewsUiState()
    data class Error(val exception: Throwable): LatestNewsUiState() {
        fun showError(exception: Throwable) {
            Log.d("MyLog", "Failure: ", exception)
        }
    }
}
