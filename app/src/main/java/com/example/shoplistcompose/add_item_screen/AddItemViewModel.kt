package com.example.shoplistcompose.add_item_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoplistcompose.data.AddItem
import com.example.shoplistcompose.data.AddItemRepository
import com.example.shoplistcompose.data.ShoppingListItem
import com.example.shoplistcompose.dialog.DialogController
import com.example.shoplistcompose.dialog.DialogEvent
import com.example.shoplistcompose.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val repository: AddItemRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(), DialogController {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var itemsList: Flow<List<AddItem>>? = null
    var addItem: AddItem? = null
    var shoppingListItem: ShoppingListItem? = null
    var listId: Int = -1

    init {
        listId = savedStateHandle.get<String>("listId")?.toInt()!!
        itemsList = repository.getAllItemsById(listId)
        viewModelScope.launch {
            shoppingListItem = repository.getShoppingListItemById(listId)
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
                    val name = addItem?.name ?: itemText.value
                    if (name.isEmpty()) {
                        sendUiEvent(UiEvent.ShowSnackBar("Name can't be empty!"))
                        return@launch
                    }
                    repository.insertItem(
                        AddItem(
                            id = addItem?.id,
                            name = addItem?.name ?: itemText.value,
                            isCheck = addItem?.isCheck ?: false,
                            listId = listId
                        )
                    )
                    itemText.value = ""
                    addItem = null
                }
                updateShoppingListCount()
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
                updateShoppingListCount()
            }
            is AddItemEvent.OnCheckedChange -> {
                viewModelScope.launch {
                    repository.insertItem(event.item)
                }
                updateShoppingListCount()
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
                addItem = addItem?.copy(name = editableText.value)
                editableText.value = ""
                onEvent(AddItemEvent.OnItemSave)
            }
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
        }
    }

    private fun updateShoppingListCount() {
        viewModelScope.launch {
            itemsList?.collect { list ->
                var counter = 0
                list.forEach { item ->
                    if(item.isCheck) counter++
                }
                shoppingListItem?.copy(
                    allItemsCount = list.size,
                    allSelectedItemsCount = counter
                )?.let { shItem ->
                    repository.insertItem(
                        shItem
                    )
                }
            }
        }
    }
    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}