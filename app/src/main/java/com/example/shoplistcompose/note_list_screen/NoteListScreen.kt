package com.example.shoplistcompose.note_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoplistcompose.dialog.MainDialog
import com.example.shoplistcompose.ui.theme.BlueLight
import com.example.shoplistcompose.ui.theme.EmptyText
import com.example.shoplistcompose.ui.theme.GrayLight
import com.example.shoplistcompose.ui.theme.Red
import com.example.shoplistcompose.utils.UiEvent

@Composable
fun NoteListScreen(
    viewModel: NoteListViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val itemsList = viewModel.noteList.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate -> {
                    onNavigate(uiEvent.route)
                }
                is UiEvent.ShowSnackBar -> {
                    val result = snackbarHostState.showSnackbar(
                        message = uiEvent.message,
                        actionLabel = "Undo"
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(NoteListEvent.UnDoneDeleteItem)
                    }
                }
                else -> {}
            }
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = BlueLight,
                    actionColor = Color.White,
                    modifier = Modifier.padding(bottom = 150.dp)
                )
            }
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayLight),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            items(itemsList.value) {item ->
                UiNoteItem(item) { event ->
                    viewModel.onEvent(event)
                }
            }
        }
        MainDialog(dialogController = viewModel)
        if(itemsList.value.isEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(),
                text = "Empty",
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                color = EmptyText
            )
        }
    }
}