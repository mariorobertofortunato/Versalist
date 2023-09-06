package com.evenclose.versalistpro.presentation.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Diversity1
import androidx.compose.material.icons.outlined.EmojiPeople
import androidx.compose.material.icons.outlined.EventNote
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Spa
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import com.evenclose.versalistpro.data.model.ListCategory.HEALTH
import com.evenclose.versalistpro.data.model.ListCategory.MISC
import com.evenclose.versalistpro.data.model.ListCategory.PERSONAL
import com.evenclose.versalistpro.data.model.ListCategory.SHOPPING
import com.evenclose.versalistpro.data.model.ListCategory.SOCIAL
import com.evenclose.versalistpro.data.model.ListCategory.WORK
import com.evenclose.versalistpro.presentation.composables.MainListItem
import com.evenclose.versalistpro.presentation.composables.placeholder.EmptyListPlaceholder
import com.evenclose.versalistpro.presentation.ui.theme.background
import com.evenclose.versalistpro.presentation.ui.theme.onDark
import com.evenclose.versalistpro.presentation.ui.theme.onLight
import com.evenclose.versalistpro.presentation.ui.theme.primary
import com.evenclose.versalistpro.presentation.ui.theme.secondary
import com.evenclose.versalistpro.presentation.ui.theme.secondaryContainer
import com.evenclose.versalistpro.presentation.viewmodel.ListViewModel
import kotlinx.coroutines.delay

@Composable
fun MainScreen(
    navController: NavController,
    listViewModel: ListViewModel = hiltViewModel(),
) {

    val mainList = listViewModel.mainList.observeAsState(emptyList())

    /** New List Form */
    var newListValue by remember { mutableStateOf("") }
    var newListFormVisibility by remember { mutableStateOf(false) }
    var errorTextVisibility by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val listTypeOptions = listOf("Open list", "Checklist")
    val (selectedListTypeOption, onListTypeOptionSelected) = remember {
        mutableStateOf(
            listTypeOptions[0]
        )
    }
    val listCategoryOptions = listOf("Personal", "Work", "Health", "Shopping", "Social", "Misc")
    val (selectedListCategoryOption, onListCategoryOptionSelected) = remember {
        mutableStateOf(
            listCategoryOptions[0]
        )
    }

    /** lazyColumn */
    val listState = rememberLazyListState(0)


    // We fetch the main list at the start of the screen.
    LaunchedEffect(Unit) {
        listViewModel.fetchAllLists()
    }

    // When a new item is added we scroll to the bottom of the list
    LaunchedEffect(mainList.value.size, newListFormVisibility) {
        listState.animateScrollToItem(mainList.value.size)
    }

    // The delay is needed as the focus must not be requested before or during the composition of the text field
    LaunchedEffect(newListFormVisibility) {
        if (newListFormVisibility) {
            delay(150)
            focusRequester.requestFocus()
        }
    }


    Scaffold(
        /** Top Bar */
        topBar = {
            Column(
                modifier = Modifier
                    .background(secondary)
            ) {
                /** HEADER */
                MainScreenHeader(
                    navController = navController
                )
            }
        },
        bottomBar = {
            AnimatedVisibility(
                visible = !newListFormVisibility,
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
                        newListFormVisibility = true
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
                            text = "New list",
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
                .border(
                    width = 1.5.dp,
                    color = onLight,
                    shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                )
                .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                .fillMaxSize()
                .padding(it)
                .background(primary)
        ) {

            /** MAIN LIST */
            if (mainList.value?.isNotEmpty() == true) {
                items(
                    items = mainList.value!!,
                    key = { item -> item }
                ) { item ->

                    AnimatedVisibility(
                        visible = !newListFormVisibility,
                    ) {
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
                                color = secondary,
                                thickness = 1.dp,
                                modifier = Modifier
                                    .fillMaxWidth(0.95f)
                            )
                        }
                    }

                }

            } else {
                /** PLACEHOLDER */
                item {
                    AnimatedVisibility(
                        visible = !newListFormVisibility,
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
                                EmptyListPlaceholder(type = "mainScreenPlaceholder")
                            }
                        }
                    }
                }
            }
            /** Add new List Form */
            item {
                AnimatedVisibility(
                    visible = newListFormVisibility,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                        horizontalAlignment = CenterHorizontally
                    ) {
                        /** Text field */
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 4.dp),
                        ) {
                            Text(
                                text = "New list name:",
                                color = onLight,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                        TextField(
                            value = newListValue,
                            onValueChange = { newValue ->
                                newListValue = newValue
                                errorTextVisibility = false
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(8.dp),
                            placeholder = {
                                Text(
                                    text = "New list",
                                    color = secondaryContainer,
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
                                .border(1.dp, secondary, RoundedCornerShape(12.dp)),
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
                                text = "Please enter a name for the list",
                                color = secondary,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        /** List type "radio" group */
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 4.dp, top = 8.dp),
                        ) {
                            Text(
                                text = "List type:",
                                color = onLight,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(background, RoundedCornerShape(12.dp))
                                .border(1.dp, secondary, RoundedCornerShape(12.dp)),
                        ) {
                            listTypeOptions.forEach { text ->
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .border(
                                            width = 1.dp,
                                            color = secondary,
                                            shape = RoundedCornerShape(12.dp)
                                        )
                                        .weight(1f)
                                        .selectable(
                                            selected = (text == selectedListTypeOption),
                                            onClick = { onListTypeOptionSelected(text) }
                                        )
                                        .clickable(
                                            // Disable ripple effect because it sucks
                                            indication = null,
                                            interactionSource = remember { MutableInteractionSource() },
                                            onClick = { onListTypeOptionSelected(text) }
                                        )
                                        .background(
                                            color = if (text == selectedListTypeOption) secondary else background,
                                            shape = RoundedCornerShape(12.dp)
                                        )
                                ) {
                                    Icon(
                                        imageVector = if (text == "Open list") Icons.Outlined.List else Icons.Outlined.Checklist,
                                        contentDescription = "List Type Icon",
                                        tint = if (text == selectedListTypeOption) onDark else onLight,
                                        modifier = Modifier
                                            .padding(top = 8.dp, bottom = 8.dp, end = 4.dp)
                                    )
                                    Text(
                                        text = text,
                                        color = if (text == selectedListTypeOption) onDark else onLight,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }

                        /** List Category Group */
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 4.dp, top = 8.dp),
                        ) {
                            Text(
                                text = "List category:",
                                color = onLight,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            contentPadding = PaddingValues(4.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .height(135.dp)
                                .fillMaxWidth()
                                .background(background, RoundedCornerShape(12.dp))
                                .border(1.dp, secondary, RoundedCornerShape(12.dp)),
                        ) {
                            items(
                                items = listCategoryOptions,
                                key = { item -> item }) { category ->
                                Box(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .border(
                                            width = 1.dp,
                                            color = secondary,
                                            shape = RoundedCornerShape(12.dp)
                                        )
                                        .weight(1f)
                                        .selectable(
                                            selected = (category == selectedListCategoryOption),
                                            onClick = { onListCategoryOptionSelected(category) }
                                        )
                                        .clickable(
                                            // Disable ripple effect because it sucks
                                            indication = null,
                                            interactionSource = remember { MutableInteractionSource() },
                                            onClick = { onListCategoryOptionSelected(category) }
                                        )
                                        .background(
                                            color = if (category == selectedListCategoryOption) secondary else background,
                                            shape = RoundedCornerShape(12.dp)
                                        )
                                ) {
                                    Column(
                                        horizontalAlignment = CenterHorizontally,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(4.dp)
                                    ) {
                                        val categoryIcon = when (category) {
                                            PERSONAL -> {
                                                Icons.Outlined.EmojiPeople
                                            }

                                            WORK -> {
                                                Icons.Outlined.Badge
                                            }

                                            HEALTH -> {
                                                Icons.Outlined.Spa
                                            }

                                            SHOPPING -> {
                                                Icons.Outlined.ShoppingCart
                                            }

                                            SOCIAL -> {
                                                Icons.Outlined.Diversity1
                                            }

                                            MISC -> {
                                                Icons.Outlined.EventNote
                                            }

                                            else -> {
                                                Icons.Outlined.List
                                            }
                                        }
                                        Icon(
                                            imageVector = categoryIcon,
                                            contentDescription = "category icon",
                                            tint = if (category == selectedListCategoryOption) onDark else onLight,
                                        )
                                        Text(
                                            text = category,
                                            color = if (category == selectedListCategoryOption) onDark else onLight,
                                            fontWeight = FontWeight.Bold,
                                            maxLines = 1,
                                        )
                                    }
                                }
                            }
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
                                    newListFormVisibility = false
                                    newListValue = ""
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
                                    if (newListValue != "") {
                                        listViewModel.addNewList(
                                            newListValue,
                                            selectedListTypeOption,
                                            selectedListCategoryOption
                                        )
                                        newListFormVisibility = false
                                        newListValue = ""
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
                                        contentDescription = "Confirm Icon",
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