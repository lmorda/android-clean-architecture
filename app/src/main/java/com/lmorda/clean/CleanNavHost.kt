package com.lmorda.clean

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lmorda.explore.details.DetailsScreenRoute
import com.lmorda.explore.list.ExploreScreenRoute

const val routeExplore = "explore"
const val routeDetailsBase = "details"
const val argDetailsId = "id"
const val routeDetailsFull = "$routeDetailsBase/{$argDetailsId}"

@Composable
internal fun CleanNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = routeExplore,
    ) {
        composable(route = routeExplore) {
            ExploreScreenRoute(
                viewModel = hiltViewModel(),
                onNavigateToDetails = { id ->
                    navController.navigate("$routeDetailsBase/$id")
                },
            )
        }
        composable(
            route = routeDetailsFull,
            arguments = listOf(
                navArgument(name = argDetailsId) { type = NavType.LongType },
            ),
        ) {
            DetailsScreenRoute(
                viewModel = hiltViewModel(),
                onBack = {
                    navController.navigateUp()
                },
            )
        }
    }
}
