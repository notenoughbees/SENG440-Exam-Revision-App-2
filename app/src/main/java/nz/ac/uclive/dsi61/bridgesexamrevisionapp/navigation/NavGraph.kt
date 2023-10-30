package nz.ac.uclive.dsi61.bridgesexamrevisionapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import nz.ac.uclive.dsi61.bridgesexamrevisionapp.CollectionListScreen
import nz.ac.uclive.dsi61.bridgesexamrevisionapp.ViewEntryScreen
import nz.ac.uclive.dsi61.bridgesexamrevisionapp.screens.Screens
import nz.ac.uclive.dsi61.bridgesexamrevisionapp.MainScreen
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

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
            route = Screens.ViewEntry.route,
            arguments = listOf(navArgument("id") { //TODO: navigation w/ arg [3/3]
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val bridgeId = backStackEntry.arguments?.getInt("id") ?: -1
            ViewEntryScreen(LocalContext.current, navController, bridgeId)
        }
    }
}