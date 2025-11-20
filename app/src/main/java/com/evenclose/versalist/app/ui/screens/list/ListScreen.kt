package com.evenclose.versalist.app.ui.screens.list

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.evenclose.versalist.R
import com.evenclose.versalist.app.compositions.LocalCompositionListScreen
import com.evenclose.versalist.app.contracts.ListScreenEvent
import com.evenclose.versalist.app.contracts.ListScreenEvent.*
import com.evenclose.versalist.app.contracts.MainScreenEvent
import com.evenclose.versalist.app.ui.composables.Loader
import com.evenclose.versalist.app.ui.composables.VersalistFab
import com.evenclose.versalist.app.ui.composables.dialog.PopupWrapper
import com.evenclose.versalist.app.ui.composables.dialog.contents.DeleteItemDialog
import com.evenclose.versalist.app.ui.composables.dialog.contents.ListScreenHelpDialog
import com.evenclose.versalist.app.ui.composables.forms.NewItemForm
import com.evenclose.versalist.app.ui.composables.item.innerlistitem.CheckListItem
import com.evenclose.versalist.app.ui.composables.item.innerlistitem.OpenListItem
import com.evenclose.versalist.app.ui.composables.placeholder.EmptyListPlaceholder
import com.evenclose.versalist.app.ui.screens.list.header.ListScreenHeader
import com.evenclose.versalist.app.ui.theme.backgroundGradient
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.app.viewmodel.ListScreenViewModel
import com.evenclose.versalist.data.model.InnerListItem
import com.evenclose.versalist.domain.model.ListTypeKey
import com.evenclose.versalist.utils.enums.PlaceholderType
import kotlinx.coroutines.delay

@Composable
fun ListScreen(
    onNavigateUp: () -> Unit,
    mainListItemId: Int,
    mainListItemName: String,
    mainListItemType: String,
    listScreenViewModel: ListScreenViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    val state by listScreenViewModel.state.collectAsState()
    var newItemTextFieldVisibility by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val listState = rememberLazyListState()

    LaunchedEffect(mainListItemId) {
        state.eventTunnel(FetchInnerList(mainListItemId))
    }

    LaunchedEffect(state.toastMessage) {
        state.toastMessage?.let { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(state.innerListItems.size, newItemTextFieldVisibility) {
        listState.animateScrollToItem(state.innerListItems.size)
    }

    // The delay is needed as the focus must not be requested before or during the composition of the text field
    LaunchedEffect(newItemTextFieldVisibility) {
        if (newItemTextFieldVisibility) {
            delay(150)
            focusRequester.requestFocus()
        }
    }

    CompositionLocalProvider(
        LocalCompositionListScreen provides state.eventTunnel
    ) {
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
                    listName = mainListItemName
                )
            },
            bottomBar = {
                AnimatedVisibility(
                    visible = !newItemTextFieldVisibility,
                    enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                    exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
                    modifier = Modifier
                        .padding(16.dp)
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

            // List of items
            AnimatedVisibility(
                visible = !newItemTextFieldVisibility,
                enter = slideInHorizontally(initialOffsetX = { it }) + fadeIn(),
                exit = slideOutHorizontally(targetOffsetX = { it }) + fadeOut(),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                LazyColumn(
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = if (state.innerListItems.isNotEmpty() || newItemTextFieldVisibility) Arrangement.Top else Arrangement.Center,
                    state = listState
                ) {
                    if (state.innerListItems.isNotEmpty()) {
                        items(
                            items = state.innerListItems,
                            key = { item -> item }
                        ) { item ->

                            when (mainListItemType) {
                                ListTypeKey.OPEN_LIST -> {
                                    OpenListItem(
                                        innerListItem = item,
                                    )
                                }

                                ListTypeKey.CHECKLIST -> {
                                    CheckListItem(
                                        innerListItem = item,
                                    )
                                }
                            }
                            HorizontalDivider(
                                color = primaryWhite.copy(alpha = 0.15f),
                                thickness = 2.dp,
                                modifier = Modifier
                                    .fillMaxWidth(0.95f)
                            )

                        }
                    } else {
                        item {
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
                        state.eventTunnel(
                            AddNewInnerListItem(
                                InnerListItem(
                                    name = newItemValue,
                                    isChecked = false,
                                    mainListId = mainListItemId
                                )
                            )
                        )
                        newItemTextFieldVisibility = false
                    }
                )
            }
        }

        if (state.isLoading) {
            Loader()
        }

        if (state.popupType != null) {
            PopupWrapper(
                onDismiss = {
                    state.eventTunnel(HidePopup)
                },
                popupType = state.popupType,
                selectedInnerListItem = state.selectedItem
            )
        }

    }


}