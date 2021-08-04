package io.navendra.retrofitkotlindeferred.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsClient
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class NewsListPageModel : ViewModel() {

    private val _newsPageFlow = MutableStateFlow(LatestNewsPageUiState.Success(emptyList()))

    val newsPageFlow: StateFlow<LatestNewsPageUiState> = _newsPageFlow

    fun loadData(){

        val service = SportNewsClient.SPORT_NEWS_API

        viewModelScope.launch(Dispatchers.Main) {
            try {
                val userRequest = service.getNews()
                _newsPageFlow.value =
                    LatestNewsPageUiState.Success(userRequest.items) // Записываем, успешно или нет всё прошло

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

    /*fun getItemByID(itemID: String?): NewsItem? {
        return news.find { it.id == itemID }
    }*/

    override fun onCleared() {
        super.onCleared()
        Log.i("SportViewModel", "SportViewModel destroyed!")
    }

}

sealed class LatestNewsPageUiState {
    data class Success(val news: List<NewsItem>): LatestNewsPageUiState()
    object Loading : LatestNewsPageUiState()
    data class Error(val exception: Throwable): LatestNewsPageUiState() {
        fun showError(exception: Throwable) {
            Log.d("MyLog", "Failure: ", exception)
        }
    }
}
