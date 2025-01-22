package com.example.shoplistcompose.add_item_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoplistcompose.data.AddItem
import com.example.shoplistcompose.data.AddItemRepository
import com.example.shoplistcompose.dialog.DialogController
import com.example.shoplistcompose.dialog.DialogEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddItemViewModel @Inject constructor(
    private val repository: AddItemRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(), DialogController {

    var itemsList: Flow<List<AddItem>>? = null
    var addItem: AddItem? = null
    var listId: Int = -1

    init {
        listId = savedStateHandle.get<String>("listId")?.toInt()!!
        itemsList = listId.let {
            repository.getAllItemsById(it)
        }
    }

    var itemText = mutableStateOf("")
        private set

    override var dialogTitle = mutableStateOf("Edit name:")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(true)
        private set

    fun onEvent(event: AddItemEvent) {
        when (event) {
            is AddItemEvent.OnItemSave -> {
                viewModelScope.launch {
                    if (listId == -1) return@launch
                    repository.insertItem(
                        AddItem(
                            id = addItem?.id,
                            name = itemText.value,
                            isCheck = addItem?.isCheck ?: false,
                            listId = listId
                        )
                    )
                    itemText.value = ""
                    addItem = null
                }
            }
            is AddItemEvent.OnShowEditDialog -> {
                addItem = event.item
                openDialog.value = true
                editableText.value = addItem?.name ?: ""
            }
            is AddItemEvent.OnTextChange -> {
                itemText.value = event.text
            }
            is AddItemEvent.OnDelete -> {
                viewModelScope.launch {
                    repository.deleteItem(event.item)
                }
            }
            is AddItemEvent.OnCheckedChange -> {
                viewModelScope.launch {
                    repository.insertItem(event.item)
                }
            }
        }
    }

    override fun onDialogEvent(event: DialogEvent) {
        when (event) {
            is DialogEvent.OnCancel -> {
                openDialog.value = false
                editableText.value = ""
            }
            is DialogEvent.OnConfirm -> {
                openDialog.value = false
                itemText.value = editableText.value
                editableText.value = ""
            }
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
        }
    }

}