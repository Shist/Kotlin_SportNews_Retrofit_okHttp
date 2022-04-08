package com.compose_ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.compose_ui.theme.Purple200
import domain.NewsItem

@Composable
fun EmptyNewsItemsList() {
    LazyColumn (
        modifier = Modifier
            .padding(all = 4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
    }
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun NewsItemsList(newsItemsList: List<NewsItem>, currItemId: MutableState<String>) {
    if (newsItemsList.isEmpty()) {
        EmptyNewsItemsList()
    } else {
        val columnsCount = integerResource(id = R.integer.columnsCount)
        LazyVerticalGrid(columns = GridCells.Fixed(columnsCount),
            modifier = Modifier
                .padding(all = 4.dp)) {
            itemsIndexed(newsItemsList) { index, item ->
                if (index % 2 == 0) {
                    AngularNewsItem(item, 8, currItemId)
                } else {
                    AngularNewsItem(item, 7, currItemId)
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun StandardNewsItem(item: NewsItem, currItemId: MutableState<String>) {
    val configuration = LocalConfiguration.current
    val itemWidth = configuration.screenWidthDp.dp
    val itemImageWidth = itemWidth / 2
    val itemImageHeight = itemImageWidth * 9 / 16
    Surface(onClick = {
        currItemId.value = item.itemId },
        modifier = Modifier
            .padding(all = 2.dp),
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
                    text = item.shortHeadline.orEmpty(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(all = 4.dp),
                )
                Text(
                    text = item.altText.orEmpty(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(all = 4.dp),
                    color = Purple200
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun AngularNewsItem(item: NewsItem, anglesNumber: Int, currItemId: MutableState<String>) {
    val configuration = LocalConfiguration.current
    val itemWidth = configuration.screenWidthDp.dp
    val itemImageWidth = itemWidth / 2
    val itemImageHeight = itemImageWidth * 9 / 16
    Surface(onClick = {
        currItemId.value = item.itemId },
        modifier = Modifier
            .padding(all = 2.dp),
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
                        text = item.shortHeadline.orEmpty(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(all = 4.dp),
                    )
                    Text(
                        text = item.altText.orEmpty(),
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
                        text = item.shortHeadline.orEmpty(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(all = 4.dp),
                    )
                    Text(
                        text = item.altText.orEmpty(),
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


@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun NewsItemsListWithNavigator(navController: NavController, newsItemsList: List<NewsItem>) {
    if (newsItemsList.isEmpty()) {
        EmptyNewsItemsList()
    } else {
        val columnsCount = integerResource(id = R.integer.columnsCount)
        LazyVerticalGrid(columns = GridCells.Fixed(columnsCount),
            modifier = Modifier
                .padding(all = 4.dp)) {
            itemsIndexed(newsItemsList) { index, item ->
                if (index % 2 == 0) {
                    AngularNewsItemWithNavigator(navController, item, 8)
                } else {
                    AngularNewsItemWithNavigator(navController, item, 7)
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun StandardNewsItemWithNavigator(navController: NavController, item: NewsItem) {
    val configuration = LocalConfiguration.current
    val itemWidth = configuration.screenWidthDp.dp
    val itemImageWidth = itemWidth / 2
    val itemImageHeight = itemImageWidth * 9 / 16
    Surface(onClick = {
        val itemId = item.itemId
        navController.navigate("${Screen.NewsItemDetails.name}/$itemId") },
        modifier = Modifier
            .padding(all = 2.dp),
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
                    text = item.shortHeadline.orEmpty(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(all = 4.dp),
                )
                Text(
                    text = item.altText.orEmpty(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(all = 4.dp),
                    color = Purple200
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun AngularNewsItemWithNavigator(navController: NavController, item: NewsItem, anglesNumber: Int) {
    val configuration = LocalConfiguration.current
    val itemWidth = configuration.screenWidthDp.dp
    val itemImageWidth = itemWidth / 2
    val itemImageHeight = itemImageWidth * 9 / 16
    Surface(onClick = {
        val itemId = item.itemId.substringAfterLast('/')
        navController.navigate("${Screen.NewsItemDetails.name}/$itemId") },
        modifier = Modifier
            .padding(all = 2.dp),
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
                        text = item.shortHeadline.orEmpty(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(all = 4.dp),
                    )
                    Text(
                        text = item.altText.orEmpty(),
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
                        text = item.shortHeadline.orEmpty(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(all = 4.dp),
                    )
                    Text(
                        text = item.altText.orEmpty(),
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
