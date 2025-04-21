package com.telect.dailyquotes.ui.theme.navigation

import com.telect.dailyquotes.R

sealed class BottomNavItems(val route: String, val icon: Int, val label: String) {
    data object Home : BottomNavItems("home,", R.drawable.ic_home, "Home")
    data object Search : BottomNavItems("search", R.drawable.ic_search, "Search")
    data object Favorite : BottomNavItems("favorite", R.drawable.ic_favorite, "Favorite")
}