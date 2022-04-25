package com.compose_ui

import android.content.Context
import android.text.Html
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import domain.NewsItemDetails

@OptIn(ExperimentalPagerApi::class)
@ExperimentalCoilApi
@Composable
fun NewsItemDetails(newsItemsDetailsList: List<NewsItemDetails>, currItemId: String,
                    webPageIsOn: MutableState<Boolean>, context: Context) {
    val configuration = LocalConfiguration.current
    val imageWidth = configuration.screenWidthDp.dp
    val imageHeight = imageWidth * 9 / 16
    val needItemIndex = newsItemsDetailsList.indexOfFirst { it.itemId == currItemId }
    val pagerState = rememberPagerState(initialPage = needItemIndex)
    if (webPageIsOn.value) {
        val currItem = newsItemsDetailsList[needItemIndex]
        AndroidView(factory = {
            WebView(context).apply {
                webViewClient = WebViewClient()
                try {
                    // TODO что-то не так с ссылкой, нужно по-другому задавать
                    loadUrl("https://api.beinsports.com/" + currItem.uri!!)
                } catch (e: Throwable) {
                    throw NullPointerException("Error! Uri of current item is null!!!\n" + e.message)
                }
            }
        })
    } else {
        HorizontalPager(state = pagerState, count = newsItemsDetailsList.size) { currItemIndex ->
            val currItem = newsItemsDetailsList[currItemIndex]
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                item {
                    Text(
                        text = currItem.shortHeadline.orEmpty(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(all = 4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }
                item {
                    Image(
                        painter = rememberImagePainter(currItem.context),
                        contentDescription = "News image",
                        modifier = Modifier
                            .size(width = imageWidth, height = imageHeight)
                            .padding(all = 4.dp)
                    )
                }
                item {
                    Text(
                        text = Html.fromHtml(currItem.body, Html.FROM_HTML_MODE_LEGACY).toString(),
                        modifier = Modifier
                            .padding(all = 4.dp),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}