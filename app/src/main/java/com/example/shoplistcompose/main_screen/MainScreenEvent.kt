package com.example.shoplistcompose.main_screen

sealed class MainScreenEvent {
    data object OnShowEditDialog : MainScreenEvent()
    data object OnItemSave : MainScreenEvent()
}