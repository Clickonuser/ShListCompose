package com.example.shoplistcompose.shoppig_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoplistcompose.ui.theme.GrayLight

@Composable
fun ShoppingListScreen(
    viewModel: ShoppingListViewModel = hiltViewModel()
) {
    val itemsList = viewModel.list.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayLight),
    ) {
        items(itemsList.value) {
            UiShoppingListItem(it)
        }
    }
}