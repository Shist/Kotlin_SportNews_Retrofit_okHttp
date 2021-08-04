package io.navendra.retrofitkotlindeferred.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsClient
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class NewsListViewModel : ViewModel() {

    var news: List<NewsItem> = emptyList()

    lateinit var newsFlow: Flow<List<NewsItem>>
    private val refreshIntervalMs: Long = 5000

    fun loadData(){

        val service = SportNewsClient.SPORT_NEWS_API

        viewModelScope.launch(Dispatchers.Main) {
            try {
                val userRequest = service.getNews()
                newsFlow = flow {
                    while(true) {
                        emit(userRequest.items) // Emits the result of the request to the flow
                        delay(refreshIntervalMs) // Suspends the coroutine for some time
                    }
                }
                newsFlow.collect { value -> news = value }
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
        viewModelScope.launch {
            newsFlow.collect { value -> news = value }
        }
        return news.find { it.id == itemID }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SportViewModel", "SportViewModel destroyed!")
    }

}
