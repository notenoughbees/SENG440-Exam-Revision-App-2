package nz.ac.uclive.dsi61.bridgesexamrevisionapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
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
    var isAddBridgeDialogOpen = remember { mutableStateOf(false) }

    Scaffold (
        topBar = {
            MyTopBar()
        },

        bottomBar = {
            BottomAppBar ( // TODO: no {}
                containerColor = Color.Magenta, //TODO: THEMING
                contentPadding = PaddingValues(16.dp),
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly //TODO: how do we add multiple things here? -> must make another element & add it to that BUT NOT IN THIS SITUATION BC IT SQUISHES THE BUTTONS/TEXT NEXT TO EACH OTHER
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "View Bridges")
                            IconButton(
                                onClick = {
                                    navController.navigate(Screens.CollectionList.route)
                                }
                            ) {
                                Icon(Icons.Filled.List, null)
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "Add Bridge")
                            IconButton(
                                onClick = {
                                    isAddBridgeDialogOpen.value = true
                                }
                            ) {
                                Icon(Icons.Filled.Add, null)
                            }
                        }
                    }
                }
            )
        }

    ) {innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(text = "Bridges Walked On:")
            Text(text = getSharedPref(context, "bridges_list").split("\n").size.toString())
        }





        if(isAddBridgeDialogOpen.value) { //TODO SHEET: have AlertDialog NOT in separate fn bc we get probs with mutaState, things not bing set directly, typeerrors... So just have here, in main code
            AlertDialog(
                onDismissRequest = {
                    isAddBridgeDialogOpen.value = false
                },
                confirmButton = {
                    Button (
                        onClick = {
                            isAddBridgeDialogOpen.value = false
                            navController.navigate(Screens.ViewEntry.route)
                        }
                    ) {
                        Text(text = "Submit")
                    }
                },
                title = { Text(text = "Add a Bridge") },
                text = { //TODO
                    Column () {
                        MyTextField("hello", bridgeName) {            newVal ->
                            bridgeName = newVal
                            setSharedPref(context, "bridge_name", newVal)
                        }
                        MyTextField("hello", bridgeLength) {            newVal ->
                            bridgeLength = newVal
                            setSharedPref(context, "bridge_length", newVal)
                        }

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
