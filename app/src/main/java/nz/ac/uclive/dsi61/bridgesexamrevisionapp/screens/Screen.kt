package nz.ac.uclive.dsi61.bridgesexamrevisionapp.screens

sealed class Screens(val route: String) {
    object Main: Screens("main")
    object CollectionList: Screens("collection_list")
    object ViewEntry: Screens("view_entry")
}