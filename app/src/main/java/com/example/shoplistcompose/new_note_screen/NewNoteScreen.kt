package com.example.shoplistcompose.new_note_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoplistcompose.R
import com.example.shoplistcompose.ui.theme.BlueLight
import com.example.shoplistcompose.ui.theme.DarkText
import com.example.shoplistcompose.ui.theme.GrayLight

@Composable
fun NewNoteScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GrayLight)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp), // changes may come later
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    TextField(
                        shape = RectangleShape,
                        modifier = Modifier.weight(1f),
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = "Title...",
                                fontSize = 14.sp
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = BlueLight,
                            unfocusedIndicatorColor = BlueLight,
                            focusedLabelColor = BlueLight,
                            cursorColor = BlueLight
                        ),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkText
                        ),
                        singleLine = true
                    )
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.save_icon),
                            contentDescription = "Save",
                            tint = BlueLight
                        )
                    }
                }
                TextField(
                    shape = RectangleShape,
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = {

                    },
                    label = {
                        Text(
                            text = "Description...",
                            fontSize = 14.sp
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
                        fontSize = 14.sp,
                        color = DarkText
                    )
                )
            }
        }
    }
}