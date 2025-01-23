package com.example.shoplistcompose.add_item_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoplistcompose.R
import com.example.shoplistcompose.dialog.MainDialog
import com.example.shoplistcompose.ui.theme.BlueLight
import com.example.shoplistcompose.ui.theme.DarkText
import com.example.shoplistcompose.ui.theme.GrayLight

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddItemScreen(
    viewModel: AddItemViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val itemsList = viewModel.itemsList?.collectAsState(initial = emptyList())

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayLight)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .windowInsetsPadding(WindowInsets.systemBars), //
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White), //
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        modifier = Modifier.weight(1f),
                        value = viewModel.itemText.value,
                        onValueChange = { text ->
                            viewModel.onEvent(AddItemEvent.OnTextChange(text))
                        },
                        label = {
                            Text(
                                text = "New item",
                                fontSize = 12.sp
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = BlueLight,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedLabelColor = BlueLight,
                            cursorColor = BlueLight
                        ),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = DarkText
                        ),
                        singleLine = true
                    )
                    IconButton(
                        onClick = {
                            viewModel.onEvent(AddItemEvent.OnItemSave)
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_icon),
                            contentDescription = "Add"
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp)
            ) {
                if (itemsList != null) {
                    items(itemsList.value) {item ->
                        UiAddItem(item = item) { event ->
                            viewModel.onEvent(event)
                        }

                    }
                }
            }
        }
        MainDialog(dialogController = viewModel)
    }
}