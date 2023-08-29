package com.evenclose.versalistpro.presentation.screens.main

import android.provider.CalendarContract
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.evenclose.versalistpro.presentation.composables.EmptyListPlaceholder
import com.evenclose.versalistpro.presentation.composables.MainListItem
import com.evenclose.versalistpro.presentation.composables.NewItemDialog
import com.evenclose.versalistpro.presentation.ui.theme.grey
import com.evenclose.versalistpro.presentation.ui.theme.inversePrimary
import com.evenclose.versalistpro.presentation.ui.theme.primary
import com.evenclose.versalistpro.presentation.ui.theme.primaryContainer
import com.evenclose.versalistpro.presentation.ui.theme.secondary
import com.evenclose.versalistpro.presentation.ui.theme.white
import com.evenclose.versalistpro.presentation.viewmodel.ListViewModel
import kotlinx.coroutines.delay

@Composable
fun MainScreen(
    navController: NavController,
    listViewModel: ListViewModel = hiltViewModel(),
) {

    val mainList = listViewModel.mainList.observeAsState(emptyList())

    var newListValue by remember { mutableStateOf("") }
    var newListTextFieldVisibility by remember { mutableStateOf(false) }
    var errorTextVisibility by remember { mutableStateOf(false) }
    val radioOptions = listOf("Open list", "Checklist")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    val listState = rememberLazyListState()

    val focusRequester = remember { FocusRequester() }

    // We fetch the main list at the start of the app
    LaunchedEffect(Unit) {
        listViewModel.fetchAllLists()
    }

    // When a new item is added we scroll to the bottom of the list
    LaunchedEffect(mainList.value.size, newListTextFieldVisibility) {
        listState.animateScrollToItem(mainList.value.size)
    }

    // The delay is needed as the focus must not be requested before or during the composition of the text field
    LaunchedEffect(newListTextFieldVisibility){
        if (newListTextFieldVisibility) {
            delay(150)
            focusRequester.requestFocus()
        }
    }


    Scaffold(
        /** Top Bar */
        topBar = {
            Column(
                modifier = Modifier
                    .background(primary)
            ) {
                /** HEADER */
                MainScreenHeader(
                    navController = navController
                )
                /** SPACER */
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(inversePrimary)
                )
            }


        },
        /** New list FAB */
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            AnimatedVisibility(
                visible = !newListTextFieldVisibility,
                enter = slideInVertically() + fadeIn(),
                exit = slideOutVertically() + fadeOut()
            ) {
                FloatingActionButton(
                    containerColor = secondary,
                    contentColor = white,
                    shape = RoundedCornerShape(50),
                    onClick = {
                        newListTextFieldVisibility = true
                        errorTextVisibility = false
                    },
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = "Add new list",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = white,
                        )
                    }

                }
            }

        },
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .padding(it)
                .background(primaryContainer)
                .border(2.dp, inversePrimary)
        ) {

            if (mainList.value?.isNotEmpty() == true) {
                /** MAIN LIST */
                items(
                    items = mainList.value!!,
                    key = { item -> item }
                ) { item ->
                    Column(
                        horizontalAlignment = CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        MainListItem(
                            mainListItem = item,
                            navController = navController
                        )
                        Divider(
                            color = grey,
                            thickness = 1.dp,
                            modifier = Modifier
                                .fillMaxWidth(0.95f)
                        )
                    }
                }
            } else {
                /** PLACEHOLDER */
                item {
                    AnimatedVisibility(
                        visible = !newListTextFieldVisibility,
                    ){
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
                                EmptyListPlaceholder(type = "mainScreenPlaceholder")
                            }
                        }
                    }
                }
            }
            /** Add new List Footer */
            item {
                AnimatedVisibility(
                    visible = newListTextFieldVisibility,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                        horizontalAlignment = CenterHorizontally
                    ) {

                        /** Text field */
                        TextField(
                            value = newListValue,
                            onValueChange = { newValue ->
                                newListValue = newValue
                                errorTextVisibility = false
                            },
                            singleLine = true,
                            shape = RectangleShape,
                            placeholder = { Text(text = "New list", color = primary) },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = white,
                                unfocusedContainerColor = white,
                                disabledContainerColor = white,
                                focusedTextColor = primary
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester)
                        )

                        /** Error Text */
                        AnimatedVisibility(
                            visible = errorTextVisibility,
                        ) {
                            Text(
                                text = "Please enter a value",
                                color = secondary,
                            )
                        }

                        /** Radio Group */
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
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

                        /** Icon Group */
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            IconButton(
                                onClick = {
                                    newListTextFieldVisibility = false
                                    newListValue = ""
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Cancel,
                                    contentDescription = "Cancel Icon",
                                    tint = white,
                                    // As these icon serves as the main way to accept and cancel the input we want them to be BIG
                                    modifier = Modifier.fillMaxSize(1f)
                                )
                            }
                            IconButton(
                                onClick = {
                                    if (newListValue != "") {
                                        listViewModel.addNewList(newListValue, selectedOption)
                                        newListTextFieldVisibility = false
                                        newListValue = ""
                                    } else {
                                        errorTextVisibility = true
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.CheckCircle,
                                    contentDescription = "Ok Icon",
                                    tint = white,
                                    // As these icon serves as the main way to accept and cancel the input we want them to be BIG
                                    modifier = Modifier.fillMaxSize(1f)
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}