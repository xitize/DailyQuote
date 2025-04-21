package com.telect.dailyquotes.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.telect.dailyquotes.ui.theme.screen.FavoriteScreen
import com.telect.dailyquotes.ui.theme.screen.HomeScreen
import com.telect.dailyquotes.ui.theme.screen.SearchScreen
import com.telect.dailyquotes.vm.MainViewModel

@Composable
fun MyBottomNavGraph(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(
        navController,
        startDestination = BottomNavItems.Home.route,
    ) {
        composable(BottomNavItems.Home.route) { HomeScreen(viewModel) }
        composable(BottomNavItems.Search.route) { SearchScreen() }
        composable(BottomNavItems.Favorite.route) { FavoriteScreen() }
    }
}