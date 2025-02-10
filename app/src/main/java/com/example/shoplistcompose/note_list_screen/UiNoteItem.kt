package com.example.shoplistcompose.note_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoplistcompose.data.NoteItem
import com.example.shoplistcompose.ui.theme.BlueLight
import com.example.shoplistcompose.ui.theme.LightText
import com.example.shoplistcompose.ui.theme.Red
import com.example.shoplistcompose.utils.Routes

@Composable
fun UiNoteItem(
    titleColor: String,
    item: NoteItem,
    onEvent: (NoteListEvent) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 3.dp,
                top = 3.dp,
                end = 3.dp
            )
            .clickable {
                onEvent(NoteListEvent.OnItemClick(
                    Routes.NEW_NOTE + "/${item.id}"
                ))
            },
        shape = RoundedCornerShape(5.dp) // changes may come later
    ) {
        Column(Modifier
            .fillMaxWidth()
            .background(Color.White) // changes may come later
        ) {
            Row(Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .padding(
                            top = 10.dp,
                            start = 10.dp,
                        )
                        .weight(1f),
                    text = item.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(android.graphics.Color.parseColor(titleColor))
                )
                Text(
                    modifier = Modifier.padding(
                        top = 10.dp,
                        end = 10.dp
                    ),
                    text = item.time,
                    color = BlueLight,
                    fontSize = 12.sp
                )
            }
            Row(Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .padding(
                            top = 5.dp,
                            start = 10.dp,
                            bottom = 10.dp
                        )
                        .weight(1f)
                    ,
                    text = item.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = LightText
                )
                IconButton(
                    onClick = {
                        onEvent(NoteListEvent.OnShowDeleteDialog(item))
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Red
                    )
                }
            }
        }
    }
}