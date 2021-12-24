package com.compose_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.compose_ui.theme.Purple200
import domain.NewsItem
import domain.NewsItemDetails
import domain.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDate

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
        val configuration = LocalConfiguration.current
        val itemWidth = configuration.screenWidthDp.dp
        val itemImageWidth = itemWidth / 2
        val itemImageHeight = itemImageWidth * 9 / 16
        Surface(
            modifier = Modifier
                .padding(all = 2.dp)
                .clickable {

                },
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.05f)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = rememberImagePainter(item.context),
                    contentDescription = "My content description",
                    modifier = Modifier
                        .height(itemImageHeight)
                        .weight(weight = 0.5f)
                        .padding(all = 4.dp),
                )
                Column(modifier = Modifier
                    .weight(0.5f)
                    .padding(all = 4.dp)) {
                    Text(
                        text = item.shortHeadline!!
                    )
                    Text(
                        text = item.altText!!,
                        color = Purple200
                    )
                }
            }
        }
    }

}
