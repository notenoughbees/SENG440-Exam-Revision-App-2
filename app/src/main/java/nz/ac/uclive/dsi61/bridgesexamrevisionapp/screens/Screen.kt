package nz.ac.uclive.dsi61.bridgesexamrevisionapp.screens

sealed class Screens(val route: String) {
    object Main: Screens("main")
    object CollectionList: Screens("collection_list")

    object ViewEntry: Screens("view_entry/{id}")
    fun passId(id: Int): String { //TODO: navigation w/ arg [2/3]
        return "view_entry/$id"
    }
}