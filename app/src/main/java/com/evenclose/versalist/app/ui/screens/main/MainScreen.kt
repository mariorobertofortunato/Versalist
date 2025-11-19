package com.evenclose.versalist.app.ui.screens.main

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
import com.evenclose.versalist.app.compositions.LocalCompositionMainScreen
import com.evenclose.versalist.app.contracts.MainScreenEvent.AddNewMainListItem
import com.evenclose.versalist.app.contracts.MainScreenEvent.HidePopup
import com.evenclose.versalist.app.ui.composables.Loader
import com.evenclose.versalist.app.ui.composables.VersalistFab
import com.evenclose.versalist.app.ui.composables.dialog.PopupWrapper
import com.evenclose.versalist.app.ui.composables.forms.NewListForm
import com.evenclose.versalist.app.ui.composables.item.MainListItem
import com.evenclose.versalist.app.ui.composables.placeholder.EmptyListPlaceholder
import com.evenclose.versalist.app.ui.theme.backgroundGradient
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.app.viewmodel.MainScreenSingularity
import com.evenclose.versalist.utils.enums.PlaceholderType
import kotlinx.coroutines.delay

@Composable
fun MainScreen(
    onNavigateToListId: (Int) -> Unit,
    mainScreenSingularity: MainScreenSingularity = hiltViewModel()
) {
    val context = LocalContext.current
    val state by mainScreenSingularity.state.collectAsState()
    var newListFormVisibility by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val listState = rememberLazyListState(0)

    LaunchedEffect(state.toastMessage) {
        state.toastMessage?.let { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(state.mainListItems.size, newListFormVisibility) {
        listState.animateScrollToItem(state.mainListItems.size)
    }

    // The delay is needed as the focus must not be requested before or during the composition of the text field
    LaunchedEffect(newListFormVisibility) {
        if (newListFormVisibility) {
            delay(150)
            focusRequester.requestFocus()
        }
    }

    CompositionLocalProvider(
        LocalCompositionMainScreen provides state.eventTunnel
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
                MainScreenHeader()
            },
            bottomBar = {
                AnimatedVisibility(
                    visible = !newListFormVisibility,
                    enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                    exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
                ) {
                    VersalistFab(
                        text = stringResource(R.string.new_list),
                        onClick = {
                            newListFormVisibility = true
                        }
                    )
                }
            },
        ) { paddingValues ->

            // List
            AnimatedVisibility(
                visible = !newListFormVisibility,
                enter = slideInHorizontally(initialOffsetX = { it }) + fadeIn(),
                exit = slideOutHorizontally(targetOffsetX = { it }) + fadeOut(),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                LazyColumn(
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = if (state.mainListItems.isNotEmpty() || newListFormVisibility) Arrangement.Top else Arrangement.Center,
                    state = listState,
                ) {
                    if (state.mainListItems.isNotEmpty()) {
                        items(
                            items = state.mainListItems,
                            key = { item -> item }
                        ) { item ->

                            MainListItem(
                                mainListItem = item,
                                onNavigateToListId = { listId ->
                                    onNavigateToListId(listId)
                                }
                            )
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
                                type = PlaceholderType.PLACEHOLDER_MAIN_SCREEN
                            )
                        }
                    }
                }
            }

            /// Form
            AnimatedVisibility(
                visible = newListFormVisibility,
                enter = slideInHorizontally(initialOffsetX = { -it }) + fadeIn(),
                exit = slideOutHorizontally(targetOffsetX = { -it }) + fadeOut(),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                NewListForm(
                    focusRequester = focusRequester,
                    onCancelClick = {
                        newListFormVisibility = false
                    },
                    onConfirmClick = { newListItem ->
                        state.eventTunnel(AddNewMainListItem(newListItem))
                        newListFormVisibility = false
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
                selectedMainListItem = state.selectedItem
            )
        }

    }
}