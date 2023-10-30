package nz.ac.uclive.dsi61.bridgesexamrevisionapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import nz.ac.uclive.dsi61.bridgesexamrevisionapp.ui.theme.BridgesExamRevisionAppTheme
import nz.ac.uclive.dsi61.bridgesexamrevisionapp.navigation.NavGraph
import nz.ac.uclive.dsi61.bridgesexamrevisionapp.screens.Screens

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BridgesExamRevisionAppTheme {
                Scaffold {
                    val navController = rememberNavController()
                    NavGraph(navController)
                }
            }
        }
    }
}

fun getSharedPref(context: Context, prefKey: String): String {
    val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//    context.getSharedPreferences("MyPrefs", 0).edit().clear().commit() //TODO: removing sharedprefs for testing
    return (sharedPrefs.getString(prefKey, "") ?: "")
}

fun setSharedPref(context: Context, prefKey: String, newVal: String) {
    val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    sharedPrefs.edit().putString(prefKey, newVal).apply()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar() {
    TopAppBar(title = { Text(text = "Danielle's Amazing Bridge Game") } )
}



@Composable
fun MyBottomBar(navController: NavController, isAddBridgeDialogOpen: MutableState<Boolean>) {
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
                    Text(text = "Main")
                    IconButton(
                        onClick = {
                            navController.navigate(Screens.Main.route)
                        }
                    ) {
                        Icon(Icons.Filled.Person, null)
                    }
                }
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
