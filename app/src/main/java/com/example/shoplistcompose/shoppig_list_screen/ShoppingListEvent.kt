package com.example.shoplistcompose.shoppig_list_screen

import com.example.shoplistcompose.data.ShoppingListItem

sealed class ShoppingListEvent {
    data class OnShowDeleteDialog(val item: ShoppingListItem) : ShoppingListEvent()
    data class OnShowEditDialog(val item: ShoppingListItem) : ShoppingListEvent()
    data class OnItemClick(val route: String) : ShoppingListEvent() // will have list id
    data object OnItemSave : ShoppingListEvent()
}