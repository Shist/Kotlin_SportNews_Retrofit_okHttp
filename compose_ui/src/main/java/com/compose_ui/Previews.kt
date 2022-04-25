package com.compose_ui

import android.content.res.Configuration
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
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
    "Women's Champions League draw",
    "1\t1803102\t<p>Holders Barcelona will face rivals Real Madrid in the quarter-finals of this season's Women's Champions League after the sides were paired together in Monday's draw for the knockout phase.</p><p>Barcelona, with new Women's Ballon d'Or winner Alexia Putellas, hammered Chelsea 4-0 in last season's final and also won a domestic league and cup double.</p><p>They won all six games in this season's inaugural Champions League group stage, scoring 24 goals and conceding just one.</p><p>Real, meanwhile, finished second in their group behind French champions Paris Saint-Germain.</p><p>The winners of the all-Spanish tie will go through to a quarter-final against either Arsenal or Wolfsburg.</p><p>The Germans pipped Chelsea to qualify for the last eight, while Arsenal -- the only English club ever to have won the title -- came second in their section behind Barcelona.</p><p>The other half of the draw is set up for a potential all-French semi-final showdown between PSG and record seven-time European champions Lyon.</p><p>PSG will take on Bayern Munich in the quarter-finals, with the winner of that going through to play Lyon or Juventus in the last four.</p><p>Juve are hoping to go all the way to the final which will be played at their home ground in Turin at a date to be confirmed.</p><p>&nbsp;</p><p>Draw</p><p>Quarter-finals</p><p>Bayern Munich (GER) v Paris Saint-Germain (FRA)</p><p>Juventus (ITA) v Lyon (FRA)</p><p>Arsenal (ENG) v Wolfsburg (GER)</p><p>Real Madrid (ESP) v Barcelona (ESP)</p><p>&nbsp;</p><p>Semi-finals</p><p>Real Madrid/Barcelona v Arsenal/Wolfsburg</p><p>Juventus/Lyon v Bayern Munich/Paris Saint-Germain</p><p>&nbsp;</p><p>- Quarter-finals to be played March 22/23 and 30/31</p><p>- Semi-finals to be played April 23/24 and April 30/May 1</p>\t1639958400\thttps://images.beinsports.com/lbC99KH5kQB05f29Mn7IpDMKiVM=/800x450/4080544-2021-12-15T195957Z_1016770655_UP1EHCF1JJVC0_RTRMADP_3_SOCCER-CHAMPIONS-FCB-KOG-REPORT.JPG\tWomen's Champions League draw\t4080544-2021-12-15T195957Z_1016770655_UP1EHCF1JJVC0_RTRMADP_3_SOCCER-CHAMPIONS-FCB-KOG-REPORT.JPG"
)


val someNewsItemDetails = domain.NewsItemDetails(
    "1803102",
    "Some item details description...",
    LocalDate.of(2021, 12, 22),
    "https://images.beinsports.com/lbC99KH5kQB05f29Mn7IpDMKiVM=/800x450/4080544-2021-12-15T195957Z_1016770655_UP1EHCF1JJVC0_RTRMADP_3_SOCCER-CHAMPIONS-FCB-KOG-REPORT.JPG",
    "Women's Champions League draw",
    "1\t1803102\t<p>Holders Barcelona will face rivals Real Madrid in the quarter-finals of this season's Women's Champions League after the sides were paired together in Monday's draw for the knockout phase.</p><p>Barcelona, with new Women's Ballon d'Or winner Alexia Putellas, hammered Chelsea 4-0 in last season's final and also won a domestic league and cup double.</p><p>They won all six games in this season's inaugural Champions League group stage, scoring 24 goals and conceding just one.</p><p>Real, meanwhile, finished second in their group behind French champions Paris Saint-Germain.</p><p>The winners of the all-Spanish tie will go through to a quarter-final against either Arsenal or Wolfsburg.</p><p>The Germans pipped Chelsea to qualify for the last eight, while Arsenal -- the only English club ever to have won the title -- came second in their section behind Barcelona.</p><p>The other half of the draw is set up for a potential all-French semi-final showdown between PSG and record seven-time European champions Lyon.</p><p>PSG will take on Bayern Munich in the quarter-finals, with the winner of that going through to play Lyon or Juventus in the last four.</p><p>Juve are hoping to go all the way to the final which will be played at their home ground in Turin at a date to be confirmed.</p><p>&nbsp;</p><p>Draw</p><p>Quarter-finals</p><p>Bayern Munich (GER) v Paris Saint-Germain (FRA)</p><p>Juventus (ITA) v Lyon (FRA)</p><p>Arsenal (ENG) v Wolfsburg (GER)</p><p>Real Madrid (ESP) v Barcelona (ESP)</p><p>&nbsp;</p><p>Semi-finals</p><p>Real Madrid/Barcelona v Arsenal/Wolfsburg</p><p>Juventus/Lyon v Bayern Munich/Paris Saint-Germain</p><p>&nbsp;</p><p>- Quarter-finals to be played March 22/23 and 30/31</p><p>- Semi-finals to be played April 23/24 and April 30/May 1</p>\t1639958400\thttps://images.beinsports.com/lbC99KH5kQB05f29Mn7IpDMKiVM=/800x450/4080544-2021-12-15T195957Z_1016770655_UP1EHCF1JJVC0_RTRMADP_3_SOCCER-CHAMPIONS-FCB-KOG-REPORT.JPG\tWomen's Champions League draw\t4080544-2021-12-15T195957Z_1016770655_UP1EHCF1JJVC0_RTRMADP_3_SOCCER-CHAMPIONS-FCB-KOG-REPORT.JPG"
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
    val webPageIsOn = rememberSaveable { mutableStateOf(false) }
    NewsItemsAppTheme {
        NewsItemDetails(newsItemsDetailsList = listOf(someNewsItemDetails, someNewsItemDetails,
            someNewsItemDetails), currItemId = someNewsItemDetails.itemId, webPageIsOn, LocalContext.current)
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
