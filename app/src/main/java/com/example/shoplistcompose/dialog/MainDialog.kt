package com.example.shoplistcompose.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoplistcompose.ui.theme.BlueLight
import com.example.shoplistcompose.ui.theme.DarkText

@Composable
fun MainDialog(dialogController: DialogController) {
    if (dialogController.openDialog.value) {
        AlertDialog(
            containerColor = White,
            onDismissRequest = {
                dialogController.onDialogEvent(DialogEvent.OnCancel)
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = dialogController.dialogTitle.value,
                        style = TextStyle(
                            color = DarkText,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    if (dialogController.showEditableText.value) {
                        TextField(
                            value = dialogController.editableText.value,
                            onValueChange = {
                                dialogController.onDialogEvent(DialogEvent.OnTextChange(it))
                            },
                            label = {
                                Text(text = "List name:")
                            },
                            colors = TextFieldDefaults.colors(
                                cursorColor = BlueLight,
                                focusedLabelColor = BlueLight,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(4.dp),
                            singleLine = true,
                            textStyle = TextStyle(
                                color = DarkText,
                                fontSize = 16.sp
                            )
                        )
                    }
                }
            } ,
            confirmButton = {
                TextButton(onClick = {
                    dialogController.onDialogEvent(DialogEvent.OnConfirm)
                }) {
                    Text(
                        text = "OK",
                        color = BlueLight
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    dialogController.onDialogEvent(DialogEvent.OnCancel)
                }) {
                    Text(
                        text = "Cancel",
                        color = BlueLight
                    )
                }
            },
        )
    }
}