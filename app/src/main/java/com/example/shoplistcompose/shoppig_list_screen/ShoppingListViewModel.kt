package com.example.shoplistcompose.shoppig_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoplistcompose.data.ShoppingListItem
import com.example.shoplistcompose.data.ShoppingListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val repository: ShoppingListRepository
) : ViewModel() {

    private var listItem: ShoppingListItem? = null

    fun onEvent(event: ShoppingListEvent) {
        when (event) {
            is ShoppingListEvent.OnItemSave -> {
                viewModelScope.launch {
                    repository.insertItem(
                        ShoppingListItem(
                            id = listItem?.id,
                            name = "list 1", // will be from dialog
                            time = "12-12-2024 13:00", // will be from special function
                            allItemsCount = listItem?.allItemsCount ?: 0,
                            allSelectedItemsCount = listItem?.allSelectedItemsCount ?: 0
                        )
                    )
                }
            }
            is ShoppingListEvent.OnItemClick -> {

            }
            is ShoppingListEvent.OnShowEditDialog -> {
                listItem = event.item
            }
            is ShoppingListEvent.OnShowDeleteDialog -> {

            }
        }
    }
}