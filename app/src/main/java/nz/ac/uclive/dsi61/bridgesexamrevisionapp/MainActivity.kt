package nz.ac.uclive.dsi61.bridgesexamrevisionapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import nz.ac.uclive.dsi61.bridgesexamrevisionapp.ui.theme.BridgesExamRevisionAppTheme
import nz.ac.uclive.dsi61.bridgesexamrevisionapp.navigation.NavGraph

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
    return (sharedPrefs.getString(prefKey, "<defaultValue>") ?: "<defaultValue>")
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
