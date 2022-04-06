package com.compose_ui

import android.content.res.Configuration
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.compose_ui.theme.NewsItemsAppTheme
import java.time.LocalDate

val someNewsItem = domain.NewsItem(
    "1803102",
    "UWCL",
    LocalDate.of(2021, 12, 22),
    "https://images.beinsports.com/lbC99KH5kQB05f29Mn7IpDMKiVM=/800x450/4080544-2021-12-15T195957Z_1016770655_UP1EHCF1JJVC0_RTRMADP_3_SOCCER-CHAMPIONS-FCB-KOG-REPORT.JPG",
    "Women's Champions League draw"
)


val someNewsItemDetails = domain.NewsItemDetails(
    "1803102",
    "Some item details description...",
    LocalDate.of(2021, 12, 22),
    "https://images.beinsports.com/lbC99KH5kQB05f29Mn7IpDMKiVM=/800x450/4080544-2021-12-15T195957Z_1016770655_UP1EHCF1JJVC0_RTRMADP_3_SOCCER-CHAMPIONS-FCB-KOG-REPORT.JPG",
    "Women's Champions League draw"
)

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
@Preview(name = "Light NewsItemsListPreview")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark NewsItemsListPreview"
)
fun NewsItemsListPreview() {
    val navHostController = rememberNavController()
    NewsItemsAppTheme {
        NewsItemsListWithNavigator(navHostController, listOf(someNewsItem, someNewsItem, someNewsItem))
    }
}

@ExperimentalCoilApi
@Composable
@Preview(name = "Light EmptyNewsItemsListPreview")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark EmptyNewsItemsListPreview"
)
fun EmptyNewsItemsListPreview() {
    EmptyNewsItemsList()
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
@Preview(name = "Light StandardNewsItemPreview")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark StandardNewsItemPreview"
)
fun StandardNewsItemPreview() {
    val navHostController = rememberNavController()
    NewsItemsAppTheme {
        StandardNewsItemWithNavigator(navHostController, someNewsItem)
    }
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
@Preview(name = "Light EvenAngularNewsItemPreview")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark EvenAngularNewsItemPreview"
)
fun EvenAngularNewsItemPreview() {
    val navHostController = rememberNavController()
    NewsItemsAppTheme {
        AngularNewsItemWithNavigator(navHostController, someNewsItem, 8)
    }
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
@Preview(name = "Light OddAngularNewsItemPreview")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark OddAngularNewsItemPreview"
)
fun OddAngularNewsItemPreview() {
    val navHostController = rememberNavController()
    NewsItemsAppTheme {
        AngularNewsItemWithNavigator(navHostController, someNewsItem, 7)
    }
}

@ExperimentalCoilApi
@Composable
@Preview(name = "Light NewsItemDetailsPreview")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark NewsItemDetailsPreview"
)
fun NewsItemDetailsPreview() {
    NewsItemsAppTheme {
        NewsItemDetails(currItem = someNewsItemDetails.itemId)
    }
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
@Preview(name = "LightScaffoldWithMenuPreview")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "DarkScaffoldWithMenuPreview"
)
fun MakeScaffoldWithMenuPreview() {
    NewsItemsAppTheme {
        MakeScaffoldWithMenu(false)
    }
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
@Preview(name = "LightScaffoldWithMenuPreviewLandscape")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "DarkScaffoldWithMenuPreviewLandscape"
)
fun MakeScaffoldWithMenuPreviewLandscape() {
    NewsItemsAppTheme {
        MakeScaffoldWithMenu(true)
    }
}

@Composable
@Preview(name = "LightMenuContentPreview")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "DarkMenuContentPreview"
)
fun MenuContentPreview() {
    NewsItemsAppTheme {
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        val menuPage = rememberSaveable { mutableStateOf(MenuPage.NEWS_LIST) }
        MenuContent(scaffoldState, scope, menuPage)
    }
}
