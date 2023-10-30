package nz.ac.uclive.dsi61.bridgesexamrevisionapp

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import nz.ac.uclive.dsi61.bridgesexamrevisionapp.screens.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewEntryScreen(context: Context, navController: NavController) {
    var isAddBridgeDialogOpen = remember { mutableStateOf(false) }
    Scaffold (
        topBar = {
            MyTopBar()
        },
        bottomBar = {
            MyBottomBar(navController, isAddBridgeDialogOpen)
        }
    ) {



    }
}
