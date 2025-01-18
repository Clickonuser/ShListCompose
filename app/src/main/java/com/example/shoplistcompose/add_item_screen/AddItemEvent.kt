package com.example.shoplistcompose.add_item_screen

import com.example.shoplistcompose.data.AddItem

sealed class AddItemEvent {
    data class OnDelete(val item: AddItem): AddItemEvent()
    data class OnShowEditDialog(val item: AddItem): AddItemEvent()
    data class OnTextChange(val text: String): AddItemEvent()
    data class OnCheckedChange(val item: AddItem): AddItemEvent()
    data object OnItemSave: AddItemEvent()
}