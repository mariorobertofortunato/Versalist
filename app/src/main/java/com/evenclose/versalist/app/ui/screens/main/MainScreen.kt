package com.evenclose.versalist.app.ui.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.evenclose.versalist.R
import com.evenclose.versalist.app.common.ViewState
import com.evenclose.versalist.app.ui.composables.FabContent
import com.evenclose.versalist.app.ui.composables.Loader
import com.evenclose.versalist.app.ui.composables.forms.NewListForm
import com.evenclose.versalist.app.ui.composables.item.MainListItem
import com.evenclose.versalist.app.ui.composables.placeholder.EmptyListPlaceholder
import com.evenclose.versalist.app.ui.theme.backgroundGradient
import com.evenclose.versalist.app.ui.theme.primaryBlack_Dark
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.app.viewmodel.ListViewModel
import com.evenclose.versalist.utils.enums.PlaceholderType
import kotlinx.coroutines.delay

@Composable
fun MainScreen(
    navController: NavController,
    listViewModel: ListViewModel = hiltViewModel(),
) {

    val mainList = listViewModel.mainList.collectAsState(emptyList())
    val viewState by listViewModel.viewState.collectAsState()

    /** New List Form */
    var newListFormVisibility by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    /** lazyColumn State*/
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
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(backgroundGradient)
            )
            .then(
                if (
                    //popupType != null ||
                    viewState is ViewState.Loading) {
                    Modifier
                        .blur(24.dp)
                } else {
                    Modifier
                }
            ),
        topBar = {
            Column(
                modifier = Modifier
                    .background(
                        brush = Brush.linearGradient(backgroundGradient)
                    )
                    .statusBarsPadding()
            ) {
                MainScreenHeader()
            }
        },
        bottomBar = {
            AnimatedVisibility(
                visible = !newListFormVisibility
            ) {
                FloatingActionButton(
                    containerColor = primaryBlack_Dark,
                    contentColor = primaryWhite,
                    shape = CircleShape,
                    onClick = {
                        newListFormVisibility = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .imePadding()
                        .padding(16.dp)
                        .border(1.dp, primaryWhite, CircleShape)
                ) {
                    FabContent(
                        text = stringResource(R.string.new_list)
                    )
                }
            }
        },
    ) {
        LazyColumn(
            verticalArrangement = if (mainList.value.isNotEmpty() || newListFormVisibility) Arrangement.Top else Arrangement.Center,
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            /** MAIN LIST */
            if (mainList.value.isNotEmpty()) {
                items(
                    items = mainList.value,
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
                            HorizontalDivider(
                                color = primaryWhite.copy(alpha = 0.15f),
                                thickness = 2.dp,
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
                        visible = !newListFormVisibility
                    ) {
                        EmptyListPlaceholder(
                            type = PlaceholderType.PLACEHOLDER_MAIN_SCREEN
                        )
                    }
                }
            }
            /** Add new List Form */
            item {
                AnimatedVisibility(
                    visible = newListFormVisibility,
                ) {
                    NewListForm(
                        focusRequester = focusRequester,
                        onCancelClick = {
                            newListFormVisibility = false
                        },
                        onConfirmClick = { newListValue,
                                           selectedListTypeOption,
                                           selectedListCategoryOption ->
                            listViewModel.addNewList(
                                newListValue,
                                selectedListTypeOption,
                                selectedListCategoryOption
                            )
                            newListFormVisibility = false
                        }
                    )
                }
            }
        }
    }

    when (viewState) {
        is ViewState.Done -> {
            listViewModel.resetViewState()
        }
        is ViewState.Error -> {
            listViewModel.resetViewState()
        }
        ViewState.Loading -> {
            Loader()
        }
        ViewState.None -> {
            // Do nothing
        }
    }
}