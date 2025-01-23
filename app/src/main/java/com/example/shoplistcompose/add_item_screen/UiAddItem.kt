package com.example.shoplistcompose.add_item_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoplistcompose.R
import com.example.shoplistcompose.data.AddItem
import com.example.shoplistcompose.ui.theme.BlueLight

@Composable
fun UiAddItem(item: AddItem, onEvent: (AddItemEvent) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
            .clickable {
                onEvent(AddItemEvent.OnShowEditDialog(item))
            },
        shape = RoundedCornerShape(5.dp) // changes may come later
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White), // changes may come later)
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp),
                text = item.name,
                fontSize = 12.sp
            )
            Checkbox(
                checked = item.isCheck,
                onCheckedChange = { isChecked ->
                    onEvent(AddItemEvent.OnCheckedChange(item.copy(isCheck = isChecked)))
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = BlueLight
                )
            )
            IconButton(onClick = {
                onEvent(AddItemEvent.OnDelete(item))
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.delete_icon),
                    contentDescription = "Delete"
                )
            }
        }
    }
}