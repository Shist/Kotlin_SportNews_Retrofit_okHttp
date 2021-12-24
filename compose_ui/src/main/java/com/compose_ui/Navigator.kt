package com.compose_ui

import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import domain.NewsItem
import domain.NewsItemDetails
import domain.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDate

@ExperimentalCoilApi
class Navigator : KoinComponent {

    private val newsRepository: NewsRepository by inject()

    private val newsListFlow: Flow<List<NewsItem>> = newsRepository.getItems()
    private val newsListItemsState = mutableStateListOf<NewsItem>()

    private val nullItemDetails = NewsItemDetails(
        "",
        "",
        LocalDate.of(2021, 1, 1),
        "",
        ""
    )
    private var newsItemDetailsFlow: Flow<NewsItemDetails> = emptyFlow()

    @Composable
    fun StartNavigation() {
        val coroutineScope = rememberCoroutineScope()
        val newsItemsList by newsListFlow.collectAsState(initial = newsListItemsState)
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.NewsItemsList.name
        ) {
            composable(route = Screen.NewsItemsList.name) {
                coroutineScope.launch {
                    newsRepository.loadNews()
                }
                NewsItemsList(navController, newsItemsList)
            }
            composable(
                route = "${Screen.NewsItemDetails.name}/{itemId}",
                arguments = listOf(
                    navArgument("itemId") {
                        type = NavType.StringType
                    }
                )
            ) { entry ->
                val itemId = "/contents/" + entry.arguments?.getString("itemId")
                newsItemDetailsFlow = newsRepository.getItemDetailsByID(itemId)
                val newsItemsDetails by newsItemDetailsFlow.collectAsState(initial = nullItemDetails)
                NewsItemDetails(item = newsItemsDetails)
            }
        }
    }
}