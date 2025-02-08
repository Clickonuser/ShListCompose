package com.example.shoplistcompose.settings_screen

sealed class SettingsEvent {
    data class OnItemSelected(val color: String) : SettingsEvent()
}