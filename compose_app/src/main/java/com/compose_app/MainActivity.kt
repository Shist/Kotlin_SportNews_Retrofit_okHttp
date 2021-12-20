package com.compose_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import coil.annotation.ExperimentalCoilApi
import com.compose_ui.Home
import com.compose_ui.theme.NewsItemsAppTheme
import data.koinModule.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)
            modules(dataModule)
        }

        setContent {
            NewsItemsAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Home().NewsItemsList()
                }
            }
        }
    }
}
