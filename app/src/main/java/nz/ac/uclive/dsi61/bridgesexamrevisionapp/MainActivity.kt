package nz.ac.uclive.dsi61.bridgesexamrevisionapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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