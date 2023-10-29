package nz.ac.uclive.dsi61.bridgesexamrevisionapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import nz.ac.uclive.dsi61.bridgesexamrevisionapp.CollectionListScreen
import nz.ac.uclive.dsi61.bridgesexamrevisionapp.ViewEntryScreen
import nz.ac.uclive.dsi61.bridgesexamrevisionapp.screens.Screens
import nz.ac.uclive.dsi61.bridgesexamrevisionapp.MainScreen
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController : NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Main.route
    ) {
        composable(
            route = Screens.Main.route
        ) { backStackEntry ->
            MainScreen(LocalContext.current, navController)
        }
        composable(
            route = Screens.CollectionList.route
        ) { backStackEntry ->
            CollectionListScreen(LocalContext.current, navController)
        }
        composable(
            route = Screens.ViewEntry.route
        ) { backStackEntry ->
            ViewEntryScreen(LocalContext.current, navController)
        }
    }
}