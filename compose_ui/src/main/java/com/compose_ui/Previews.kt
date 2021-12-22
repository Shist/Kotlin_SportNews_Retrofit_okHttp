package com.compose_ui

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import com.compose_ui.theme.NewsItemsAppTheme
import java.time.LocalDate

@ExperimentalCoilApi
@Composable
@Preview(name = "Light NewsItemsListPreview")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark NewsItemsListPreview"
)
fun NewsItemsListPreview() {
    NewsItemsAppTheme {
        NewsList().NewsItemsList()
    }
}

@ExperimentalCoilApi
@Composable
@Preview(name = "Light NewsItemPreview")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark NewsItemPreview"
)
fun NewsItemPreview() {
    val item = domain.NewsItem("/contents/1803102",
        "UWCL",
        LocalDate.of(2021, 12, 22),
        "https://images.beinsports.com/lbC99KH5kQB05f29Mn7IpDMKiVM=/800x450/4080544-2021-12-15T195957Z_1016770655_UP1EHCF1JJVC0_RTRMADP_3_SOCCER-CHAMPIONS-FCB-KOG-REPORT.JPG",
        "Women's Champions League draw")
    NewsItemsAppTheme {
        NewsList().NewsItem(item)
    }
}
