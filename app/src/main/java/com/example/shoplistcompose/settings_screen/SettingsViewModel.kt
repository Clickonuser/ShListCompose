package com.example.shoplistcompose.settings_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(): ViewModel() {

    val colorItemListState = mutableStateOf<List<ColorItem>>(emptyList())

    init {
        viewModelScope.launch {
            val tempColorItemList = ArrayList<ColorItem>()
            ColorUtils.colorList.forEach { color ->
                tempColorItemList.add(
                    ColorItem(
                        color = color,
                        isSelect = false
                    )
                )
            }
            colorItemListState.value = tempColorItemList
        }
    }
}