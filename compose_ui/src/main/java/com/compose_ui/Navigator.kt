package com.compose_ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.view_model.NewsListViewModel
import com.view_model.NewsPageViewModel
import domain.NewsItemDetails
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import java.time.LocalDate

@ExperimentalMaterialApi
@ExperimentalCoilApi
class Navigator : KoinComponent {

    private val nullItemDetails = NewsItemDetails(
        "",
        "",
        LocalDate.of(2021, 1, 1),
        "",
        ""
    )

    @Composable
    fun StartNavigation() {
        val coroutineScope = rememberCoroutineScope()
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.NewsItemsList.name
        ) {
            composable(route = Screen.NewsItemsList.name) {
                val newsListViewModel: NewsListViewModel by inject()
                coroutineScope.launch {
                    try {
                        newsListViewModel.loadData()
                    } catch (e: Throwable) {
                        // TODO: make different states and messages depending on problem (No Internet, Empty Database, Other...)
                    }
                }
                val newsItemsList by newsListViewModel.newsListFlow
                    .collectAsState(initial = emptyList())
                MakeScaffoldWithMenu(navController, newsItemsList)
            }
            composable(
                route = "${Screen.NewsItemDetails.name}/{itemId}",
                arguments = listOf(
                    navArgument("itemId") {
                        type = NavType.StringType
                    }
                )
            ) { entry ->
                val itemId = entry.arguments?.getString("itemId")
                val newsPageViewModel: NewsPageViewModel by inject {
                    parametersOf(itemId)
                }
                coroutineScope.launch {
                    try {
                        newsPageViewModel.loadData(itemId!!)
                    } catch (e: Throwable) {
                        // TODO: make different states and messages depending on problem (No Internet, Empty Database, Other...)
                    }
                }
                val newsItemsDetails by newsPageViewModel.newsPageFlow.collectAsState(initial = nullItemDetails)
                NewsItemDetails(item = newsItemsDetails)
            }
        }
    }
}