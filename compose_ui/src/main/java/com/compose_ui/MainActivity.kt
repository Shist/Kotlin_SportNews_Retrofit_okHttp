package com.compose_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import coil.annotation.ExperimentalCoilApi
import com.compose_ui.theme.NewsItemsAppTheme

@ExperimentalCoilApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewsItemsAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NewsList().NewsItemsList()
                }
            }
        }
    }
}
