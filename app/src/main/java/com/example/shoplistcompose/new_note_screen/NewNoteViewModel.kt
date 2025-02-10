package com.example.shoplistcompose.new_note_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoplistcompose.data.NoteItem
import com.example.shoplistcompose.data.NoteRepository
import com.example.shoplistcompose.datastore.DataStoreManager
import com.example.shoplistcompose.utils.UiEvent
import com.example.shoplistcompose.utils.getCurrentTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    private val repository: NoteRepository,
    savedStateHandle: SavedStateHandle,
    dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var noteId = -1
    private var noteItem: NoteItem? = null

    var titleColor = mutableStateOf("#FF000000")

    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set

    init {
        noteId = savedStateHandle.get<String>("noteId")?.toInt() ?: -1
        if (noteId != -1) {
            viewModelScope.launch {
                repository.getNoteItemById(noteId).let { noteItem ->
                    title = noteItem.title
                    description = noteItem.description
                    this@NewNoteViewModel.noteItem = noteItem
                }
                dataStoreManager.getStringPreference(
                    DataStoreManager.TITLE_COLOR,
                    "#FF000000"
                ).collect { color ->
                    titleColor.value = color
                }
            }
        }
    }

    fun onEvent(event: NewNoteEvent) {
        when (event) {

            is NewNoteEvent.OnSave -> {
                viewModelScope.launch {
                    if (title.isBlank()) {
                        sendUiEvent(
                            UiEvent.ShowSnackBar(
                                message = "Title can't be empty!"
                            )
                        )
                        return@launch
                    }
                    repository.insertItem(
                        NoteItem(
                            id = noteItem?.id,
                            title = title,
                            description = description,
                            time = noteItem?.time ?: getCurrentTime()
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
            is NewNoteEvent.OnTitleChange -> {
                title = event.title
            }
            is NewNoteEvent.OnDescriptionChange -> {
                description = event.description
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}