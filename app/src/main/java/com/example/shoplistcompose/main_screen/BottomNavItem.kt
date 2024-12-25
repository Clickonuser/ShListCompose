package com.example.shoplistcompose.main_screen

import com.example.shoplistcompose.R
import com.example.shoplistcompose.utils.Routes

sealed class BottomNavItem(val title: String, val iconId: Int, val route: String) {
    data object ListItem: BottomNavItem("List", R.drawable.list_icon, Routes.SHOPPING_LIST)
    data object NoteItem: BottomNavItem("Note", R.drawable.note_icon, Routes.NOTE_SCREEN)
    data object AboutItem: BottomNavItem("About", R.drawable.about_icon, Routes.ABOUT)
    data object SettingsItem: BottomNavItem("Settings", R.drawable.settings_icon, Routes.SETTINGS)
}