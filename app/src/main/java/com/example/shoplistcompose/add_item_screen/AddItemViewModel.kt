package com.example.shoplistcompose.add_item_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.shoplistcompose.data.AddItem
import com.example.shoplistcompose.data.AddItemRepository
import com.example.shoplistcompose.dialog.DialogController
import com.example.shoplistcompose.dialog.DialogEvent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddItemViewModel @Inject constructor(
    private val repository: AddItemRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(), DialogController {

    var itemsList: Flow<List<AddItem>>? = null

    init {
        val listId = savedStateHandle.get<String>("listId")?.toInt()
        itemsList = listId?.let {
            repository.getAllItemsById(it)
        }
    }

    fun onEvent(event: AddItemEvent) {

    }

    override var dialogTitle = mutableStateOf("List name:")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(true)
        private set

    override fun onDialogEvent(event: DialogEvent) {
        when (event) {
            is DialogEvent.OnCancel -> {
                openDialog.value = false
                editableText.value = ""
            }
            is DialogEvent.OnConfirm -> {
                openDialog.value = false
                editableText.value = ""
            }
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
        }
    }

}