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
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import java.time.LocalDate

private val nullItemDetails = NewsItemDetails(
    "",
    "",
    LocalDate.of(2021, 1, 1),
    "",
    ""
)

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun StartNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.NewsItemsList.name
    ) {
        composable(route = Screen.NewsItemsList.name) {
            val newsListViewModel = getViewModel<NewsListViewModel>()
            newsListViewModel.loadData()
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
            val newsPageViewModel = getViewModel<NewsPageViewModel> {
                parametersOf(itemId)
            }
            newsPageViewModel.loadData(itemId!!)
            val newsItemsDetails by newsPageViewModel.newsPageFlow.collectAsState(initial = nullItemDetails)
            NewsItemDetails(item = newsItemsDetails)
        }
    }
}