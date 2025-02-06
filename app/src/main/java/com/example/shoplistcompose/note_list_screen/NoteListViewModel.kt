package com.example.shoplistcompose.note_list_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoplistcompose.data.NoteItem
import com.example.shoplistcompose.data.NoteRepository
import com.example.shoplistcompose.dialog.DialogController
import com.example.shoplistcompose.dialog.DialogEvent
import com.example.shoplistcompose.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val repository: NoteRepository
): ViewModel(), DialogController {

    val noteList = repository.getAllItems()
    private var noteItem: NoteItem? = null

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    override var dialogTitle = mutableStateOf("Delete this note?")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(false)
        private set

    fun onEvent(event: NoteListEvent) {
        when (event) {
            is NoteListEvent.OnShowDeleteDialog -> {
                openDialog.value = true
                noteItem = event.item
            }
            is NoteListEvent.OnItemClick -> {
                sendUiEvent(UiEvent.Navigate(event.route))
            }
        }
    }

    override fun onDialogEvent(event: DialogEvent) {
        when (event) {
            is DialogEvent.OnCancel -> {
                openDialog.value = false
            }
            is DialogEvent.OnConfirm -> {
                viewModelScope.launch {
                    noteItem?.let { noteItem ->
                        repository.deleteItem(noteItem)
                    }
                }
                openDialog.value = false
            }
            else -> {}
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}