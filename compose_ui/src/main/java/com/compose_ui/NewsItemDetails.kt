package com.compose_ui

import android.text.Html
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import domain.NewsItemDetails

@ExperimentalCoilApi
@Composable
fun NewsItemDetails(item: NewsItemDetails) {
    val configuration = LocalConfiguration.current
    val imageWidth = configuration.screenWidthDp.dp
    val imageHeight = imageWidth * 9 / 16
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            Text(
                text = item.shortHeadline.orEmpty(),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(all = 4.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        item {
            Image(
                painter = rememberImagePainter(item.context),
                contentDescription = "News image",
                modifier = Modifier
                    .size(width = imageWidth, height = imageHeight)
                    .padding(all = 4.dp)
            )
        }
        item {
            Text(
                text = Html.fromHtml(item.body, Html.FROM_HTML_MODE_LEGACY).toString(),
                modifier = Modifier
                    .padding(all = 4.dp),
                fontSize = 16.sp
            )
        }
    }
}