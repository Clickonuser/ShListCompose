package com.example.shoplistcompose.main_screen

import com.example.shoplistcompose.R

sealed class BottomNavItem(val title: String, val iconId: Int, val route: String) {
    data object ListItem: BottomNavItem("List", R.drawable.list_icon, "route1")
    data object NoteItem: BottomNavItem("Note", R.drawable.note_icon, "route2")
    data object AboutItem: BottomNavItem("About", R.drawable.about_icon, "route3")
    data object SettingsItem: BottomNavItem("Settings", R.drawable.settings_icon, "route4")
}