package com.example.shoplistcompose.main_screen

sealed class MainScreenEvent {
    data object OnItemSave : MainScreenEvent()
    data class Navigate(val route: String) : MainScreenEvent()
    data class NavigateMain(val route: String) : MainScreenEvent()
    data class OnNewItemClick(val route: String) : MainScreenEvent()
}