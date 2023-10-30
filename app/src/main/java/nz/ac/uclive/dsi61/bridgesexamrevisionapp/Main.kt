package nz.ac.uclive.dsi61.bridgesexamrevisionapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nz.ac.uclive.dsi61.bridgesexamrevisionapp.screens.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(context: Context, navController: NavController) {
    var bridgeName by rememberSaveable { mutableStateOf(getSharedPref(context, "bridge_name")) }
    var bridgeLength by rememberSaveable { mutableStateOf(getSharedPref(context, "bridge_length")) }
    val isAddBridgeDialogOpen = remember { mutableStateOf(false) }

    Scaffold (
        topBar = {
            MyTopBar()
        },
        bottomBar = {
            MyBottomBar(navController, isAddBridgeDialogOpen)
        }

    ) {innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(text = "Bridges Walked On:")
            Text(text = getSharedPref(context, "bridges_list").split("\n").size.toString())
//            Text(text = "Total Distance Walked:")
//            Text(text = getSharedPref(context, "bridge_length_total").toString())
        }




        // DIALOG STUFF: NOT PART OF MAIN LAYOUT
        if(isAddBridgeDialogOpen.value) { //TODO SHEET: have AlertDialog NOT in separate fn bc we get probs with mutaState, things not bing set directly, typeerrors... So just have here, in main code
            AlertDialog(
                onDismissRequest = {
                    isAddBridgeDialogOpen.value = false
                },
                title = { Text(text = "Add a Bridge") },
                text = { //TODO: we can add components in the text! :D This is how we do textfields etc
                    Column () {
                        MyTextField("Bridge Name", bridgeName) { newVal ->
                            bridgeName = newVal
                            setSharedPref(context, "bridge_name", newVal)
                        }
                        MyTextField("Bridge Length", bridgeLength) { newVal ->
                            bridgeLength = newVal
//                            // add TO the current value instead of replacing
//                            val distanceWalkedAlready = getSharedPref(context, "bridge_length_total")
//                            Log.d("FOO", distanceWalkedAlready)
//                            Log.d("FOO", distanceWalkedAlready + newVal)
//                            setSharedPref(context, "bridge_length_total", (distanceWalkedAlready.toDoubleOrNull()
//                                ?.plus(newVal.toDoubleOrNull()!!)).toString())
                            setSharedPref(context, "bridge_length", newVal)
                        }

                    }
                },
                confirmButton = {
                    Button (
                        onClick = {
                            // add the bridge name to the list of all bridge names (a string)
                            val bridgesString = getSharedPref(context, "bridge_list")
                            val newBridgeName = getSharedPref(context, "bridge_name")
                            val newBridgeLength = getSharedPref(context, "bridge_length")
                            val bridge = newBridgeName + "\t" + newBridgeLength
                            setSharedPref(context, "bridge_list", bridgesString + "\n" + bridge)

                            isAddBridgeDialogOpen.value = false

                            val newBridgesString = getSharedPref(context, "bridge_list") //TODO: MUST GET BRIDGE LIST AGAIN AFTER SETPREFS!!!!!!!!
                            val bridgesList = newBridgesString.split("\n")
                            Log.d("[MAIN] BRIDGES LIST", bridgesList.toString())
                            Log.d("[MAIN] BRIDGE", bridge)
                            val bridgeId = bridgesList.indexOf(bridge)
                            Log.d("[MAIN] BRIDGE ID", bridgeId.toString())
                            if (bridgeId != -1) { // Check if the bridge is found in the list
                                navController.navigate(Screens.ViewEntry.passId(bridgeId)) //TODO: navigation w/ arg [1/3]
                            } else { // Handle the case where the bridge is not found in the list
                                Toast.makeText(context, "Bridge not found", Toast.LENGTH_SHORT).show()
                            }
                        }
                    ) {
                        Text(text = "Submit")
                    }
                }
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(label: String, selectedValue: String, onValueChange: (String) -> Unit) {
    TextField(
        modifier = Modifier,
        label = {Text(text = label)},
//        placeholder = {Text(text = "Enter bridge name")},
        value = selectedValue,
        onValueChange = onValueChange
    )
}
