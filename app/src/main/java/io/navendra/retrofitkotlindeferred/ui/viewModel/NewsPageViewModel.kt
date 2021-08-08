package io.navendra.retrofitkotlindeferred.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsClient
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class NewsPageViewModel : ViewModel() {

    private val _newsPageFlow = MutableStateFlow<LatestNewsUiState<NewsItem>>(LatestNewsUiState.Loading())

    val newsPageFlow: StateFlow<LatestNewsUiState<NewsItem>> = _newsPageFlow.asStateFlow()

    fun loadData(item_id: String){

        val service = SportNewsClient.SPORT_NEWS_API

        viewModelScope.launch {
            try {
                val userRequest = service.getNews()
                val item = getNewsPageByID(userRequest.items, item_id)
                _newsPageFlow.value =
                    LatestNewsUiState.Success(item)
                if(item != null){
                    Log.d("MyLog", "response item: $item")
                }else{
                    Log.d("MyLog", "Failure while getting response item...")
                }
            } catch (e: Throwable){
                Log.d("MyLog", "Failure...", e)
            }
        }
    }

    private fun getNewsPageByID(items: List<NewsItem>, item_id: String) : NewsItem? {
        return items.find { it.id == item_id }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SportViewModel", "SportViewModel destroyed!")
    }

}
