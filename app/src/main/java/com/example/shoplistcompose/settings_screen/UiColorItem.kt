package com.example.shoplistcompose.settings_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.shoplistcompose.R

@Composable
fun UiColorItem(
    item: ColorItem
) {
    IconButton(
        onClick = {},
        modifier = Modifier
            .padding(start = 10.dp)
            .clip(CircleShape)
            .size(35.dp)
            .background(
                color = Color(android.graphics.Color.parseColor(item.color))
            )
    ) {
        if (item.isSelect) Icon(
            painter = painterResource(id = R.drawable.check_icon),
            contentDescription = "Check",
            tint = Color.White
        )
    }
}