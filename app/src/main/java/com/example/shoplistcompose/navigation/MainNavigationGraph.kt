package com.example.shoplistcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoplistcompose.add_item_screen.AddItemScreen
import com.example.shoplistcompose.main_screen.MainScreen
import com.example.shoplistcompose.new_note_screen.NewNoteScreen
import com.example.shoplistcompose.utils.Routes

@Composable
fun MainNavigationGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.MAIN_SCREEN) {
        composable(Routes.ADD_ITEM + "/{listId}") {
            AddItemScreen()
        }
        composable(Routes.NEW_NOTE) {
            NewNoteScreen()
        }
        composable(Routes.MAIN_SCREEN) {
            MainScreen(navController)
        }
    }
}