package com.compose_ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.compose_ui.theme.NewsItemsAppTheme
import domain.NewsItem
import domain.NewsRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@ExperimentalCoilApi
class Home : KoinComponent {
    private val newsRepository: NewsRepository by inject()
    private val newsListFlow: Flow<List<NewsItem>> = newsRepository.getItems()
    private val newsListItemsState = mutableStateListOf<NewsItem>()

    @Composable
    fun NewsItemsList() {
        val newsItemsList by newsListFlow.collectAsState(initial = newsListItemsState)
        LazyColumn {
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
    @Preview(name = "Light NewsItemsListPreview")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark NewsItemsListPreview"
    )
    fun NewsItemsListPreview() {
        NewsItemsAppTheme {
            NewsItemsList()
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

    @Composable
    @Preview(name = "Light NewsItemPreview")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark NewsItemPreview"
    )
    fun NewsItemPreview() {
        val item = domain.NewsItem("/contents/1803102",
            "UWCL",
            LocalDate.parse("1639947600", DateTimeFormatter.ISO_OFFSET_DATE_TIME),
            "https://images.beinsports.com/lbC99KH5kQB05f29Mn7IpDMKiVM=/800x450/4080544-2021-12-15T195957Z_1016770655_UP1EHCF1JJVC0_RTRMADP_3_SOCCER-CHAMPIONS-FCB-KOG-REPORT.JPG",
        "Women's Champions League draw")
        NewsItemsAppTheme {
            NewsItem(item)
        }
    }

}
