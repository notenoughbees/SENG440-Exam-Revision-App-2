package nz.ac.uclive.dsi61.bridgesexamrevisionapp

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import nz.ac.uclive.dsi61.bridgesexamrevisionapp.screens.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionListScreen(context: Context, navController: NavController) {
    Scaffold (

    ) {
        Row(

        ) {
            Button(
                onClick = {
                    navController.navigate(Screens.Main.route)
                }
            ) {
                Text(text = "main")
            }
            Button(
                onClick = {
                    navController.navigate(Screens.CollectionList.route)
                }
            ) {
                Text(text = "COLLECTION LIST")
            }
            Button(
                onClick = {
                    navController.navigate(Screens.ViewEntry.route)
                }
            ) {
                Text(text = "view entry")
            }
        }
    }
}