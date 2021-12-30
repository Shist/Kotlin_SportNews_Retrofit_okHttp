package com.compose_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.compose_ui.theme.Purple200
import domain.NewsItem

@ExperimentalCoilApi
@Composable
fun NewsItemsList(navController: NavController, newsItemsList: List<NewsItem>) {
    LazyColumn (
        modifier = Modifier
            .padding(all = 4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (newsItemsList.isEmpty()) {
            item {
                Text(
                    text = "No any data have been loaded!\n Please, check your network connection...",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 4.dp)
                )
            }
            item {
                Image(
                    painter = painterResource(id = R.drawable.no_data_yet),
                    contentDescription = "No data yet",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 4.dp)
                )
            }
        } else {
            itemsIndexed(newsItemsList) { index, item ->
                if (index % 2 == 0) {
                    AngularNewsItem(navController, item, 8)
                } else {
                    AngularNewsItem(navController, item, 7)
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun StandardNewsItem(navController: NavController, item: NewsItem) {
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
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.05f)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberImagePainter(item.context),
                contentDescription = "News image",
                modifier = Modifier
                    .height(itemImageHeight)
                    .weight(weight = 0.5f)
                    .padding(all = 4.dp),
            )
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(all = 4.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.shortHeadline!!,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(all = 4.dp),
                )
                Text(
                    text = item.altText!!,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(all = 4.dp),
                    color = Purple200
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun AngularNewsItem(navController: NavController, item: NewsItem, anglesNumber: Int) {
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
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.05f)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (anglesNumber % 2 == 0) {
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(all = 4.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = item.shortHeadline!!,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(all = 4.dp),
                    )
                    Text(
                        text = item.altText!!,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(all = 4.dp),
                        color = Purple200
                    )
                }
                Image(
                    painter = rememberImagePainter(item.context),
                    contentDescription = "News image",
                    modifier = Modifier
                        .height(itemImageHeight)
                        .weight(weight = 0.5f)
                        .padding(all = 4.dp)
                        .graphicsLayer {
                            shape = CustomImageShape(anglesNumber)
                            clip = true
                        },
                )
            } else {
                Image(
                    painter = rememberImagePainter(item.context),
                    contentDescription = "News image",
                    modifier = Modifier
                        .height(itemImageHeight)
                        .weight(weight = 0.5f)
                        .padding(all = 4.dp)
                        .graphicsLayer {
                            shape = CustomImageShape(anglesNumber)
                            clip = true
                        },
                )
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(all = 4.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = item.shortHeadline!!,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(all = 4.dp),
                    )
                    Text(
                        text = item.altText!!,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(all = 4.dp),
                        color = Purple200
                    )
                }
            }
        }
    }
}
