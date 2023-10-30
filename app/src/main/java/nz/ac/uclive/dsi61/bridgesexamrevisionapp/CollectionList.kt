package nz.ac.uclive.dsi61.bridgesexamrevisionapp

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nz.ac.uclive.dsi61.bridgesexamrevisionapp.screens.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionListScreen(context: Context, navController: NavController) {
    var isAddBridgeDialogOpen = remember { mutableStateOf(false) }
    Scaffold (
        topBar = {
            MyTopBar()
        },
        bottomBar = {
            MyBottomBar(navController, isAddBridgeDialogOpen)
        }
    ) {
        val bridgesString = getSharedPref(context, "bridge_list")
        val bridgesList = bridgesString.split("\n")
        BridgesList(bridgesList)

    }
}


@Composable
fun BridgesList(bridgesList: List<String>) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)

    ) {
        items(bridgesList) { bridge ->
            Text(
              text = bridge
            )
        }
    }
}
