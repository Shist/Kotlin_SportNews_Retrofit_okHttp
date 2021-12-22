package com.compose_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import domain.NewsItemDetails

@ExperimentalCoilApi
@Composable
fun NewsItemDetails(item: NewsItemDetails) {
    LazyColumn {
        item {
            Text(text = item.shortHeadline!!)
        }
        item {
            Image(
                painter = rememberImagePainter(item.context),
                contentDescription = "My content description",
                modifier = Modifier
            )
        }
        item {
            Text(text = item.body!!)
        }
    }
}