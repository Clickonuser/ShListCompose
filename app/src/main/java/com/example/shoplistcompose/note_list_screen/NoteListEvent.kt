package com.example.shoplistcompose.note_list_screen

import com.example.shoplistcompose.data.NoteItem

sealed class NoteListEvent {
    data class OnShowDeleteDialog(val item: NoteItem) : NoteListEvent()
    data class OnItemClick(val route: String) : NoteListEvent()
    data class OnTextSearchChange(val text: String): NoteListEvent()
    data object UnDoneDeleteItem : NoteListEvent()
}