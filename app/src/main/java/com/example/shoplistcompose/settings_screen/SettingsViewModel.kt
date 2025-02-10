package com.example.shoplistcompose.settings_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoplistcompose.datastore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    val colorItemListState = mutableStateOf<List<ColorItem>>(emptyList())

    init {
        viewModelScope.launch {
            dataStoreManager.getStringPreference(
                key = DataStoreManager.TITLE_COLOR,
                defValue = "#FF000000"
            ).collect { selectedColor ->

                val tempColorItemList = ArrayList<ColorItem>()

                ColorUtils.colorList.forEach { color ->
                    tempColorItemList.add(
                        ColorItem(
                            color = color,
                            isSelect = selectedColor == color
                        )
                    )
                }
                colorItemListState.value = tempColorItemList
            }
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.OnItemSelected -> {
                viewModelScope.launch {
                    dataStoreManager.saveStringPreference(
                        value = event.color,
                        key = DataStoreManager.TITLE_COLOR
                    )
                }
            }
        }
    }
}