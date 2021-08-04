package io.navendra.retrofitkotlindeferred.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.navendra.retrofitkotlindeferred.model.NewsItem
import io.navendra.retrofitkotlindeferred.retrofit.SportNewsClient
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class NewsPageViewModel : ViewModel() {

    private val _newsPageFlow = MutableStateFlow<LatestNewsPageUiState>(LatestNewsPageUiState.Loading)

    val newsPageFlow: StateFlow<LatestNewsPageUiState> = _newsPageFlow

    fun loadData(item_id: String){

        val service = SportNewsClient.SPORT_NEWS_API

        viewModelScope.launch(Dispatchers.Main) {
            try {
                val userRequest = service.getNewsPageByID(item_id)
                _newsPageFlow.value =
                    LatestNewsPageUiState.Success(userRequest)
                if(userRequest != null){
                    Log.d("MyLog", "response item: $userRequest")
                }else{
                    Log.d("MyLog", "Failure while getting response item...")
                }
            } catch (e: Throwable){
                Log.d("MyLog", "Failure...", e)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("SportViewModel", "SportViewModel destroyed!")
    }

}

sealed class LatestNewsPageUiState {
    data class Success(val news_item: NewsItem?): LatestNewsPageUiState()
    object Loading : LatestNewsPageUiState()
    data class Error(val exception: Throwable): LatestNewsPageUiState() {
        fun showError(exception: Throwable) {
            Log.d("MyLog", "Failure: ", exception)
        }
    }
}
