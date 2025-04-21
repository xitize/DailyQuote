package com.telect.dailyquotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.telect.dailyquotes.ui.theme.DailyQuotesTheme
import com.telect.dailyquotes.ui.theme.navigation.BottomNavItems
import com.telect.dailyquotes.ui.theme.navigation.MyBottomNavGraph
import com.telect.dailyquotes.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    val context = LocalContext.current

    TopAppBar(
        title = { Text("DailyQuotes", fontSize = 20.sp) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF6200EE),
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        )
    )
}

@Composable
fun MyBottomNavigationBar(navController: NavHostController) {
    val items = listOf(BottomNavItems.Home, BottomNavItems.Search, BottomNavItems.Favorite)

    NavigationBar {
        val currentBackStackEntry = navController.currentBackStackEntryAsState().value
        val currentRoute = currentBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(item.icon),
                    contentDescription = item.label
                )
            }, label = { Text(item.label) }, selected = currentRoute == item.route, onClick = {
                if (currentRoute != item.route) {
                    navController.navigate(item.route) {
                        // Clear the back stack to avoid multiple copies of the same destination
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            })

        }
    }

}

@Composable
@Preview
fun MainScreen() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = hiltViewModel()

    DailyQuotesTheme {
        Scaffold(
            topBar = { MyTopAppBar() },
            snackbarHost = { SnackbarHost(viewModel.snackBarHostState) },
            bottomBar = { MyBottomNavigationBar(navController) }) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                MyBottomNavGraph(navController, viewModel)
            }
        }

    }
}

