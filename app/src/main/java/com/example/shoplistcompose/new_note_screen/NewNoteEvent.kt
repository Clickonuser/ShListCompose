package com.example.shoplistcompose.new_note_screen

sealed class NewNoteEvent {
    data class OnTitleChange(val title: String) : NewNoteEvent()
    data class OnDescriptionChange(val description: String) : NewNoteEvent()
    data object OnSave : NewNoteEvent()
}