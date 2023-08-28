package com.evenclose.versalistpro.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.evenclose.versalistpro.presentation.ui.theme.primary
import com.evenclose.versalistpro.presentation.ui.theme.primaryContainer
import com.evenclose.versalistpro.presentation.ui.theme.secondary
import com.evenclose.versalistpro.presentation.ui.theme.white
import com.evenclose.versalistpro.presentation.viewmodel.ListViewModel
import kotlinx.coroutines.delay


@Composable
fun NewItemDialog(
    type: String,
    mainListId: Int?,
    listViewModel: ListViewModel = hiltViewModel(),
    onDismissRequest: () -> Unit
) {
    var value by remember { mutableStateOf("") }
    var errorTextVisibility by remember { mutableStateOf(false) }
    val focusRequester = FocusRequester()
    val radioOptions = listOf("Open list", "Checklist")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }


    LaunchedEffect(Unit) {
        delay(150)
        focusRequester.requestFocus()
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Box(
            Modifier
                .clip(RectangleShape)
                .fillMaxWidth()
                .background(primaryContainer)
        ) {
            Column {
                /** Title*/
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
                        text = text,
                        color = white,
                    )
                }

                /** TextField */
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
                        onValueChange = {
                            value = it
                            errorTextVisibility = false
                        },
                        singleLine = true,
                        shape = RectangleShape,
                        placeholder = { Text(text = placeholder, color = primary) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = white,
                            unfocusedContainerColor = white,
                            disabledContainerColor = white,
                            focusedTextColor = primary
                        ),
                        modifier = Modifier.focusRequester(focusRequester)
                    )
                }

                /** Main List type radio button (for main list only) */
                if (mainListId == null) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        radioOptions.forEach { text ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .weight(1f)
                                    .selectable(
                                        selected = (text == selectedOption),
                                        onClick = { onOptionSelected(text) }
                                    )
                            ) {
                                RadioButton(
                                    selected = (text == selectedOption),
                                    onClick = { onOptionSelected(text) }
                                )
                                Text(
                                    text = text,
                                    color = white
                                )

                            }
                        }
                    }
                }

                /** Error Text */
                AnimatedVisibility(
                    visible = errorTextVisibility,
                    enter = fadeIn(
                        initialAlpha = 0f
                    ),
                    exit = fadeOut(
                        animationSpec = tween(durationMillis = 250)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Please enter a value",
                            color = secondary,
                        )
                    }
                }

                /** Ok Button */
                Surface(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        if (value != "") {
                            if (type == "MainListItem") {
                                listViewModel.addNewList(value, selectedOption)
                            } else {
                                if (mainListId != null) {
                                    listViewModel.addNewInnerListItem(
                                        value = value,
                                        mainListId = mainListId
                                    )
                                }
                            }
                            onDismissRequest()
                        } else {
                            errorTextVisibility = true
                        }
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
