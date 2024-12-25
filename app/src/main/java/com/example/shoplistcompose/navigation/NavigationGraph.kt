package com.example.shoplistcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shoplistcompose.about_screen.AboutScreen
import com.example.shoplistcompose.note_list_screen.NoteListScreen
import com.example.shoplistcompose.settings_screen.SettingsScreen
import com.example.shoplistcompose.shoppig_list_screen.ShoppingListScreen
import com.example.shoplistcompose.utils.Routes

@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.SHOPPING_LIST) {
        composable(Routes.SHOPPING_LIST) {
            ShoppingListScreen()
        }
        composable(Routes.NOTE_SCREEN) {
            NoteListScreen()
        }
        composable(Routes.ABOUT) {
            AboutScreen()
        }
        composable(Routes.SETTINGS) {
            SettingsScreen()
        }
    }
}