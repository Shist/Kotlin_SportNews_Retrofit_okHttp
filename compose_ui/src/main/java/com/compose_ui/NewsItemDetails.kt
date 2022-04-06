package com.compose_ui

import android.text.Html
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.view_model.NewsPageViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPagerApi::class)
@ExperimentalCoilApi
@Composable
fun NewsItemDetails(currItem: String) {
    val configuration = LocalConfiguration.current
    val imageWidth = configuration.screenWidthDp.dp
    val imageHeight = imageWidth * 9 / 16
    val newsPageViewModel = getViewModel<NewsPageViewModel>()
    newsPageViewModel.loadData(currItem)
    val newsItemsDetailsList by newsPageViewModel.newsDetailsListFlow
        .collectAsState(initial = emptyList())
    val needItem = newsPageViewModel.getItem(currItem)
        .collectAsState(initial = nullItemDetails).value
    val pagerState = rememberPagerState(pageCount = newsItemsDetailsList.size,
        initialPage = newsItemsDetailsList.indexOf(needItem))
    HorizontalPager(state = pagerState) {
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                Text(
                    text = needItem.shortHeadline.orEmpty(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(all = 4.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
            item {
                Image(
                    painter = rememberImagePainter(needItem.context),
                    contentDescription = "News image",
                    modifier = Modifier
                        .size(width = imageWidth, height = imageHeight)
                        .padding(all = 4.dp)
                )
            }
            item {
                Text(
                    text = Html.fromHtml(needItem.body, Html.FROM_HTML_MODE_LEGACY).toString(),
                    modifier = Modifier
                        .padding(all = 4.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}