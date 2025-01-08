package com.lmorda.homework

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lmorda.explore.list.ExploreScreenRoute

const val routeExplore = "explore"

@Composable
internal fun HomeworkNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = routeExplore,
    ) {
        composable(route = routeExplore) {
            ExploreScreenRoute(viewModel = hiltViewModel())
        }
    }
}
