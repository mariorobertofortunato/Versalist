package com.evenclose.versalistpro.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.evenclose.versalistpro.presentation.ui.theme.primary
import com.evenclose.versalistpro.presentation.ui.theme.primaryContainer
import com.evenclose.versalistpro.presentation.ui.theme.white
import com.evenclose.versalistpro.presentation.viewmodel.ListViewModel

@Composable
fun NewItemDialog(
    type: String,
    mainListId: Int?,
    listViewModel: ListViewModel = hiltViewModel(),
    onDismissRequest: () -> Unit
) {
    var value by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismissRequest) {
        Box(
            Modifier
                .clip(RectangleShape)
                .fillMaxWidth()
                .background(primaryContainer)
        ) {
            Column {
                /** Dialog Title*/
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(primary)
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val text = if (type == "MainListItem") {
                        "Add new list"
                    } else {
                        "Add new item"
                    }
                    Text(
                        text,
                        color = white,
                    )
                }

                /** Dialog Body*/
                Row(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    val placeholder = if (type == "MainListItem") {
                        "New list"
                    } else {
                        "New item"
                    }
                    TextField(
                        value = value,
                        onValueChange = { value = it },
                        singleLine = true,
                        shape = RectangleShape,
                        placeholder = { Text(text = placeholder, color = primary) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = white,
                            unfocusedContainerColor = white,
                            disabledContainerColor = white,
                            focusedTextColor = primary
                        )
                    )
                }
                Surface(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        // TODO Check su string vuota
                        if (type == "MainListItem") {
                            listViewModel.addNewList(value)
                        } else {
                            if (mainListId != null) {
                                listViewModel.addNewInnerListItem(value = value, mainListId = mainListId)
                            }
                        }
                        onDismissRequest()
                    },
                    shape = RectangleShape,
                    color = primary,
                ) {
                    Text(
                        text = "OK",
                        color = white,
                        modifier = Modifier
                            .widthIn(120.dp)
                            .padding(vertical = 8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun DialogPreview() {

}
