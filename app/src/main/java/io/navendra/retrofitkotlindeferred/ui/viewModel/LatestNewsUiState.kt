package io.navendra.retrofitkotlindeferred.ui.viewModel

sealed class LatestNewsUiState<T> {
        class Success<T>(val data: T?) : LatestNewsUiState<T>()
        class Loading<T> : LatestNewsUiState<T>()
        class Error<T>(val exception: Throwable?) : LatestNewsUiState<T>()
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
