package com.compose_ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import domain.NewsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class MenuPage {
    NEWS_LIST,
    SQUARE
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun MakeScaffoldWithMenu(navController: NavController, newsItemsList: List<NewsItem>) {
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
        }
    ) {
        when (menuPage.value) {
            MenuPage.NEWS_LIST -> {
                NewsItemsList(navController, newsItemsList)
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
