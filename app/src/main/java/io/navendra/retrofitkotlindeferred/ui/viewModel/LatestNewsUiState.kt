package io.navendra.retrofitkotlindeferred.ui.viewModel

sealed class LatestNewsUiState<T>(
        val data: T? = null
) {
        data class Success<T>(val news: T) : LatestNewsUiState<T>(news)
        class Loading<T>() : LatestNewsUiState<T>()
        data class Error<T>(val exception: T? = null) : LatestNewsUiState<T>(exception)
}
//sealed class LatestNewsListUiState {
//    data class Success(val news: List<NewsItem>): LatestNewsListUiState()
//    object Loading : LatestNewsListUiState()
//    data class Error(val exception: Throwable): LatestNewsListUiState()
//}

//sealed class LatestNewsPageUiState {
//    data class Success(val news_item: NewsItem?): LatestNewsPageUiState()
//    object Loading : LatestNewsPageUiState()
//    data class Error(val exception: Throwable): LatestNewsPageUiState()
//}
