package nz.ac.uclive.dsi61.bridgesexamrevisionapp

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewEntryScreen(context: Context, navController: NavController, bridgeId: Int) {
    val isAddBridgeDialogOpen = remember { mutableStateOf(false) }

    val bridgeString = getSharedPref(context, "bridge_list")
    val bridgeList = bridgeString.split("\n")
    Log.d("[VIEW] BRIDGE LIST", bridgeList.toString())

    //TODO: get the arg passed into the screen!!
    Log.d("BRIDGE ID", bridgeId.toString())

    val bridgeInfo = bridgeList[bridgeId]
    val bridgeName = bridgeInfo.split("\t")[0]
    val bridgeLength = bridgeInfo.split("\t")[1]

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
            Text (text = bridgeName)
            Text (text = bridgeLength)
        }



    }
}
