package com.evenclose.versalistpro.presentation.screens.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.evenclose.versalistpro.presentation.composables.CheckListItem
import com.evenclose.versalistpro.presentation.composables.OpenListItem
import com.evenclose.versalistpro.presentation.composables.placeholder.EmptyListPlaceholder
import com.evenclose.versalistpro.presentation.ui.theme.background
import com.evenclose.versalistpro.presentation.ui.theme.onDark
import com.evenclose.versalistpro.presentation.ui.theme.onLight
import com.evenclose.versalistpro.presentation.ui.theme.primary
import com.evenclose.versalistpro.presentation.ui.theme.secondary
import com.evenclose.versalistpro.presentation.viewmodel.ListViewModel
import kotlinx.coroutines.delay

@Composable
fun ListScreen(
    navController: NavController,
    listId: Int,
    listViewModel: ListViewModel = hiltViewModel(),
) {

    /** Current List */
    val currentListData = listViewModel.currentListData.observeAsState(null)
    val currentInnerList = listViewModel.currentInnerList.observeAsState(null)

    //var showDialog by remember { mutableStateOf(false) }

    var newItemValue by remember { mutableStateOf("") }
    var newItemTextFieldVisibility by remember { mutableStateOf(false) }
    var errorTextVisibility by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit, newItemTextFieldVisibility) {
        listViewModel.getListData(listId)
        listViewModel.getCurrentInnerList(listId)
    }


    // When a new item is added we scroll to the bottom of the list
    LaunchedEffect(currentInnerList.value?.size, newItemTextFieldVisibility) {
        currentInnerList.value?.size?.let { listState.animateScrollToItem(it) }
    }

    // The delay is needed as the focus must not be requested before or during the composition of the text field
    LaunchedEffect(newItemTextFieldVisibility) {
        if (newItemTextFieldVisibility) {
            delay(150)
            focusRequester.requestFocus()
        }
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(secondary)
            ) {

                /** HEADER */
                ListScreenHeader(
                    navController = navController,
                    listName = currentListData.value?.name ?: "Error"
                )
            }
        },
        bottomBar = {
            AnimatedVisibility(
                visible = !newItemTextFieldVisibility,
                modifier = Modifier
                    .padding(start = 2.dp, end = 2.dp, bottom = 2.dp)
                    .background(
                        color = primary,
                        shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                    )
            ) {
                FloatingActionButton(
                    containerColor = secondary,
                    contentColor = onDark,
                    shape = RoundedCornerShape(12.dp),
                    onClick = {
                        newItemTextFieldVisibility = true
                        errorTextVisibility = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .border(2.dp, primary, RoundedCornerShape(12.dp))
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Add item",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = onDark,
                        )
                    }
                }
            }

        },
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .border(1.5.dp, onLight,RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                .fillMaxSize()
                .padding(it)
                .background(primary)
        ) {

            if (currentInnerList.value?.isNotEmpty() == true) {
                /** INNER LIST */
                items(
                    items = currentInnerList.value!!,
                    key = { item -> item }
                ) { item ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        if (currentListData.value?.type == "Open list") {
                            OpenListItem(
                                innerListItem = item,
                            )
                        } else {
                            CheckListItem(
                                innerListItem = item,
                            )
                        }
                        Divider(
                            color = secondary,
                            thickness = 2.dp,
                            modifier = Modifier
                                .fillMaxWidth(0.95f)
                        )

                    }
                }
            } else {
                /** PLACEHOLDER */
                item {
                    AnimatedVisibility(
                        visible = !newItemTextFieldVisibility,
                    ) {
                        Column() {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(
                                        horizontal = 12.dp,
                                        vertical = 6.dp
                                    )
                                    .fillMaxWidth()
                            ) {
                                EmptyListPlaceholder(type = "listScreenPlaceholder")
                            }
                        }
                    }

                }
            }
            /** Add new Item */
            item {
                AnimatedVisibility(
                    visible = newItemTextFieldVisibility,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        /** Text field */
                        TextField(
                            value = newItemValue,
                            onValueChange = { newValue ->
                                newItemValue = newValue
                                errorTextVisibility = false
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp),
                            placeholder = {
                                Text(
                                    text = "New item",
                                    color = onLight,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            },
                            colors = TextFieldDefaults.colors(
                                cursorColor = secondary,
                                focusedContainerColor = background,
                                unfocusedContainerColor = background,
                                disabledContainerColor = background,
                                focusedTextColor = onLight
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester)
                                .border(1.dp, secondary,RoundedCornerShape(8.dp)),
                            textStyle = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            ),
                            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
                        )

                        /** Error Text */
                        AnimatedVisibility(
                            visible = errorTextVisibility,
                            enter = slideInVertically(animationSpec = tween(100)) + fadeIn(),
                            exit = slideOutVertically (animationSpec = tween(100)) + fadeOut()
                        ) {
                            Text(
                                text = "Please enter a value",
                                color = secondary,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        /** Confirm/Cancel Icon Group */
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, bottom = 4.dp)
                        ) {
                            FloatingActionButton(
                                containerColor = secondary,
                                contentColor = onDark,
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 4.dp)
                                    .border(2.dp, primary, RoundedCornerShape(12.dp)),
                                onClick = {
                                    newItemTextFieldVisibility = false
                                    newItemValue = ""
                                }
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Cancel,
                                        contentDescription = "Cancel Icon",
                                        tint = onDark,
                                        modifier = Modifier
                                            .padding(top = 12.dp, bottom = 12.dp, end = 8.dp)
                                    )
                                    Text(
                                        text = "Cancel",
                                        color = onDark,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                }
                            }
                            FloatingActionButton(
                                containerColor = secondary,
                                contentColor = onDark,
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 4.dp)
                                    .border(2.dp, primary, RoundedCornerShape(12.dp)),
                                onClick = {
                                    if (newItemValue != "") {
                                        listViewModel.addNewInnerListItem(
                                            value = newItemValue,
                                            mainListId = listId
                                        )
                                        newItemTextFieldVisibility = false
                                        newItemValue = ""
                                    } else {
                                        errorTextVisibility = true
                                    }
                                }
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.CheckCircle,
                                        contentDescription = "Ok Icon",
                                        tint = onDark,
                                        modifier = Modifier
                                            .padding(top = 12.dp, bottom = 12.dp, end = 8.dp)
                                    )
                                    Text(
                                        text = "Confirm",
                                        color = onDark,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}