package com.example.shoplistcompose.utils

sealed class UiEvent {
    data object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackBar(val message: String): UiEvent()
}