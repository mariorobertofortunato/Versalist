package com.evenclose.versalist.app.ui.screens.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.evenclose.versalist.R
import com.evenclose.versalist.app.ui.composables.VersalistFab
import com.evenclose.versalist.app.ui.composables.forms.NewItemForm
import com.evenclose.versalist.app.ui.composables.item.CheckListItem
import com.evenclose.versalist.app.ui.composables.item.OpenListItem
import com.evenclose.versalist.app.ui.composables.placeholder.EmptyListPlaceholder
import com.evenclose.versalist.app.ui.theme.backgroundGradient
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.app.viewmodel.ListViewModel
import com.evenclose.versalist.utils.enums.PlaceholderType
import kotlinx.coroutines.delay

@Composable
fun ListScreen(
    onNavigateUp: () -> Unit,
    listId: Int,
    listViewModel: ListViewModel = hiltViewModel(),
) {

    val state by listScreenViewModel.state.collectAsState()
    val currentListData = listViewModel.currentListData.collectAsState(null)
    val currentInnerList = listViewModel.currentInnerList.collectAsState(null)

    var newItemTextFieldVisibility by remember { mutableStateOf(false) }
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
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(backgroundGradient)
            )
            .safeContentPadding()
            .then(
                if (state.popupType != null || state.isLoading) {
                    Modifier
                        .blur(24.dp)
                } else {
                    Modifier
                }
            ),
        topBar = {
            ListScreenHeader(
                onNavigateUp = {
                    onNavigateUp()
                },
                listName = currentListData.value?.name ?: "Error"
            )
        },
        bottomBar = {
            AnimatedVisibility(
                visible = !newItemTextFieldVisibility,

                enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
            ) {
                VersalistFab(
                    text = stringResource(id = R.string.add_item),
                    onClick = {
                        newItemTextFieldVisibility = true
                    }
                )

            }

        },
    ) { paddingValues ->



        LazyColumn(
            verticalArrangement = if (currentInnerList.value?.isNotEmpty() == true || newItemTextFieldVisibility) Arrangement.Top else Arrangement.Center,
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
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
                        HorizontalDivider(
                            color = primaryWhite.copy(alpha = 0.15f),
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
                        EmptyListPlaceholder(
                            type = PlaceholderType.PLACEHOLDER_LIST_SCREEN
                        )
                    }

                }
            }
        }

        /// Form
        AnimatedVisibility(
            visible = newItemTextFieldVisibility,
            enter = slideInHorizontally(initialOffsetX = { -it }) + fadeIn(),
            exit = slideOutHorizontally(targetOffsetX = { -it }) + fadeOut(),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            NewItemForm(
                focusRequester = focusRequester,
                onCancelClick = {
                    newItemTextFieldVisibility = false
                },
                onConfirmClick = { newItemValue ->
                    listViewModel.addNewInnerListItem(
                        value = newItemValue,
                        mainListId = listId
                    )
                    newItemTextFieldVisibility = false
                }
            )
        }
    }
}