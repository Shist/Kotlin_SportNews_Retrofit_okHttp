package com.compose_ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.view_model.NewsListViewModel
import com.view_model.NewsPageViewModel
import domain.NewsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.time.LocalDate

enum class MenuPage {
    NEWS_LIST,
    SQUARE
}

val nullItemDetails = domain.NewsItemDetails("",
    "",
    LocalDate.now(),
    "",
    "")

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun MakeScaffoldWithMenu(isLandscape: Boolean) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val menuPage = rememberSaveable { mutableStateOf(MenuPage.NEWS_LIST) }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        if (scaffoldState.drawerState.isClosed) {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                        else {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                        }
                    } ) {
                        Icon(Icons.Filled.Menu, contentDescription = "menu")
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                actions = {
                    // Here we can add some extra button for Toolbar in future
                },
                backgroundColor = MaterialTheme.colors.primarySurface
            )
        },
        drawerContent = {
            MenuContent(scaffoldState, scope, menuPage)
        },
        drawerGesturesEnabled = false
    ) {
        val newsItemId = rememberSaveable { mutableStateOf("no_item_selected") }
        val newsListViewModel = getViewModel<NewsListViewModel>()
        newsListViewModel.loadData()
        val newsItemsList by newsListViewModel.newsListFlow
            .collectAsState(initial = emptyList())
        when (menuPage.value) {
            MenuPage.NEWS_LIST -> {
                val deviceIsTablet = booleanResource(id = R.bool.isTablet)
                // Если работаем с планшетом и при этом в лэндскейпной ориентации, то...
                if (deviceIsTablet && isLandscape) {
                    Row {
                        Box(modifier = Modifier.weight(0.5f)) {
                            NewsItemsList(newsItemsList, newsItemId)
                        }
                        Box(modifier = Modifier.weight(0.5f),
                            contentAlignment = Alignment.Center) {
                            if (newsItemId.value == "no_item_selected") {
                                Text(
                                    text = stringResource(id = R.string.noItemSelectedText),
                                    textAlign = TextAlign.Center,
                                    fontSize = 48.sp,
                                    modifier = Modifier
                                        .padding(all = 4.dp),
                                )
                            } else {
                                NewsItemDetails(currItem = newsItemId.value)
                            }
                        }
                    }
                } else { // Если же работаем с телефоном или с планшетом (но портретной ориентации), то...
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NewsItemsList.name
                    ) {
                        composable(route = Screen.NewsItemsList.name) {
                            when (menuPage.value) {
                                MenuPage.NEWS_LIST -> {
                                    NewsItemsListWithNavigator(navController, newsItemsList)
                                }
                                MenuPage.SQUARE -> {
                                    MakeAnimationSquare()
                                }
                            }
                        }
                        composable(
                            route = "${Screen.NewsItemDetails.name}/{itemId}",
                            arguments = listOf(
                                navArgument("itemId") {
                                    type = NavType.StringType
                                }
                            )
                        ) { entry ->
                            newsItemId.value = entry.arguments?.getString("itemId")!!
                            // Если работаем с планшетом и при этом в лэндскейпной ориентации, то...
                            if (deviceIsTablet && isLandscape) {
                                Row {
                                    Box(modifier = Modifier.weight(0.5f)) {
                                        NewsItemsListWithNavigator(navController, newsItemsList)
                                    }
                                    Box(modifier = Modifier.weight(0.5f)) {
                                        NewsItemDetails(currItem = newsItemId.value)
                                    }
                                }
                            } else { // Если же работаем с телефоном или с планшетом (но портретной ориентации), то...
                                NewsItemDetails(currItem = newsItemId.value)
                            }
                        }
                    }
                }
            }
            MenuPage.SQUARE -> {
                MakeAnimationSquare()
            }
        }
    }
}

@Composable
fun MenuContent(scaffoldState: ScaffoldState,
                scope: CoroutineScope,
                menuPage: MutableState<MenuPage>
) {
    LazyColumn(modifier = Modifier
        .padding(all = 8.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start) {
        item {
            Row(modifier = Modifier.padding(all = 8.dp)) {
                Text(text = stringResource(id = R.string.menu_name),
                    fontSize = 48.sp,
                    modifier = Modifier.padding(all = 8.dp))
            }
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth()
                    .clickable {
                        menuPage.value = MenuPage.NEWS_LIST
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    } ) {
                Icon(imageVector = Icons.Default.List,
                    contentDescription = stringResource(id = R.string.menu_item_news_list),
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .defaultMinSize(48.dp, 48.dp))
                Text(text = stringResource(id = R.string.menu_item_news_list),
                    fontSize = 24.sp,
                    modifier = Modifier.padding(all = 8.dp))
            }
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth()
                    .clickable {
                        menuPage.value = MenuPage.SQUARE
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    } ) {
                Icon(imageVector = Icons.Default.Info,
                    contentDescription = stringResource(id = R.string.menu_item_square),
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .defaultMinSize(48.dp, 48.dp))
                Text(text = stringResource(id = R.string.menu_item_square),
                    fontSize = 24.sp,
                    modifier = Modifier.padding(all = 8.dp))
            }
        }
    }
}
