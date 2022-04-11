package com.compose_ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import coil.annotation.ExperimentalCoilApi
import com.compose_ui.theme.NewsItemsAppTheme

@ExperimentalMaterialApi
@ExperimentalCoilApi
class MainActivity : ComponentActivity() {

    // TODO
    // Сделать кнопку внутри деталей, по которой откроется некая android view внутри compose, в которой откроется новость (в отдельном экране).
    // После нажатия кнпоки произойдёт перекомпоновка (колонка (заголовок, картинка, текст) исчезнет, вместо неё появится окно с новостью).
    // Это нужно будет расскоментировать uri внутри нашего Json и использовать его, чтобы перейти по ссылке.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val orientation = resources.configuration.orientation

        setContent {
            NewsItemsAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                        MakeScaffoldWithMenu(isLandscape = false)
                    } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        MakeScaffoldWithMenu(isLandscape = true)
                    }
                }
            }
        }
    }
}
