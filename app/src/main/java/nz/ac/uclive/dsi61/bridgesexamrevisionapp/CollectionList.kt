package nz.ac.uclive.dsi61.bridgesexamrevisionapp

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
    val isAddBridgeDialogOpen = remember { mutableStateOf(false) }
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
        BridgesList(bridgesList, onBridgeClick = {bridge ->
            Toast.makeText(context, bridge, Toast.LENGTH_SHORT).show()
            // the bridge id comes from getting the bridge's index in the bridges list, which is a string split by newlines
            Log.d("BRIDGES LIST", bridgesList.toString())
            Log.d("BRIDGE", bridge)
            val bridgeId = bridgesList.indexOf(bridge)
            Log.d("BRIDGE ID", bridgeId.toString())
            if (bridgeId != -1) { // Check if the bridge is found in the list
                navController.navigate(Screens.ViewEntry.passId(bridgeId)) //TODO: navigation w/ arg [1/3]
            } else { // Handle the case where the bridge is not found in the list
                Toast.makeText(context, "Bridge not found", Toast.LENGTH_SHORT).show()
            }

        })
    }
}


@Composable
fun BridgesList(bridgesList: List<String>, onBridgeClick: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)

    ) {
        items(bridgesList) { bridge -> // bridge = <name>\t<length>
            Box( //TODO: wrap in box so can make box clickable instead of just text
                modifier = Modifier
                    .clickable { onBridgeClick(bridge) },
            ) {
                Text(
                    modifier = Modifier
                        .padding(all = 32.dp),
                    text = bridge.split("\t")[0] // get the bridge name only
                )
            }

        }
    }
}
