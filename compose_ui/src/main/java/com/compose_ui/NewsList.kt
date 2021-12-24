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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.compose_ui.theme.Purple200
import domain.NewsItem

@ExperimentalCoilApi
@Composable
fun NewsItemsList(navController: NavController, newsItemsList: List<NewsItem>) {
    LazyColumn {
        if (newsItemsList.isEmpty()) {
            //TODO Make some image or text that data is empty
        } else {
            items(newsItemsList) { i ->
                NewsItem(navController, i)
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun NewsItem(navController: NavController, item: NewsItem) {
    val configuration = LocalConfiguration.current
    val itemWidth = configuration.screenWidthDp.dp
    val itemImageWidth = itemWidth / 2
    val itemImageHeight = itemImageWidth * 9 / 16
    Surface(
        modifier = Modifier
            .padding(all = 2.dp)
            .clickable {
                val itemId = item.itemId.substringAfterLast('/')
                navController.navigate("${Screen.NewsItemDetails.name}/$itemId")
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