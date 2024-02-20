package com.emperor.hpproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.emperor.hpproject.ui.characters.details.CharactersDetailsScreen
import com.emperor.hpproject.ui.characters.list.CharactersListScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavRoute.LIST.value
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavRoute.LIST.value) {
            CharactersListScreen(modifier = modifier, onClick = {
                navController.navigate(NavRoute.DETAILS.value + "/${it.id}")
            })
        }
        composable(
            NavRoute.DETAILS.value + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            CharactersDetailsScreen(modifier = modifier, onClose = {
                navController.popBackStack()
            })
        }
    }
}