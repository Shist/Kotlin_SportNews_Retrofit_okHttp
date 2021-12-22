package com.compose_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import domain.NewsItem
import domain.NewsRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@ExperimentalCoilApi
class NewsList : KoinComponent {
    private val newsRepository: NewsRepository by inject()
    private val newsListFlow: Flow<List<NewsItem>> = newsRepository.getItems()
    private val newsListItemsState = mutableStateListOf<NewsItem>()

    @Composable
    fun NewsItemsList() {
        val coroutineScope = rememberCoroutineScope()
        val newsItemsList by newsListFlow.collectAsState(initial = newsListItemsState)
        LazyColumn {
            coroutineScope.launch {
                newsRepository.loadNews()
            }
            if (newsItemsList.isEmpty()) {
                //TODO Make some image or text that data is empty
            } else {
                items(newsItemsList) { i ->
                    NewsItem(i)
                }
            }
        }
    }

    @Composable
    fun NewsItem(item: NewsItem) {
        Row {
            Image(painter = rememberImagePainter(item.context),
                contentDescription = "My content description",)
            Column {
                Text(text = item.shortHeadline!!)
                Text(text = item.altText!!)
            }
        }
    }

}
