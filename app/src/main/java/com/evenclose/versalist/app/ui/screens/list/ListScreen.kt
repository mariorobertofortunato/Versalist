package com.evenclose.versalist.app.ui.screens.list

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.evenclose.versalist.R
import com.evenclose.versalist.app.ui.composables.FabContent
import com.evenclose.versalist.app.ui.composables.forms.NewItemForm
import com.evenclose.versalist.app.ui.composables.item.CheckListItem
import com.evenclose.versalist.app.ui.composables.item.OpenListItem
import com.evenclose.versalist.app.ui.composables.placeholder.EmptyListPlaceholder
import com.evenclose.versalist.app.ui.theme.background
import com.evenclose.versalist.app.ui.theme.backgroundGradient
import com.evenclose.versalist.app.ui.theme.light
import com.evenclose.versalist.app.ui.theme.dark
import com.evenclose.versalist.app.ui.theme.primary
import com.evenclose.versalist.app.ui.theme.primaryBlack_Dark
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.app.ui.theme.secondary
import com.evenclose.versalist.utils.enums.PlaceholderType
import com.evenclose.versalist.app.viewmodel.ListViewModel
import kotlinx.coroutines.delay

@Composable
fun ListScreen(
    navController: NavController,
    listId: Int,
    listViewModel: ListViewModel = hiltViewModel(),
) {

    /** Current List */
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
            ),
        topBar = {
            Column(
                modifier = Modifier
                    .background(
                        brush = Brush.linearGradient(backgroundGradient)
                    )
            ) {
                ListScreenHeader(
                    navController = navController,
                    listName = currentListData.value?.name ?: "Error"
                )
            }
        },
        bottomBar = {
            AnimatedVisibility(
                visible = !newItemTextFieldVisibility
            ) {
                FloatingActionButton(
                    containerColor = primaryBlack_Dark,
                    contentColor = primaryWhite,
                    shape = CircleShape,
                    onClick = {
                        newItemTextFieldVisibility = true
                        //errorTextVisibility = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .border(1.dp, primaryWhite, CircleShape)
                ) {
                    FabContent(
                        text = stringResource(id = R.string.add_item)
                    )
                }
            }

        },
    ) {
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
                            color = secondary.copy(alpha = 0.25f),
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
            /** Add new Item */
            item {
                AnimatedVisibility(
                    visible = newItemTextFieldVisibility,
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
    }
}