package com.evenclose.versalistpro.presentation.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.evenclose.versalistpro.data.model.InnerListItem
import com.evenclose.versalistpro.presentation.composables.EmptyListPlaceholder
import com.evenclose.versalistpro.presentation.composables.InnerListItem
import com.evenclose.versalistpro.presentation.composables.NewItemDialog
import com.evenclose.versalistpro.presentation.ui.theme.inversePrimary
import com.evenclose.versalistpro.presentation.ui.theme.primary
import com.evenclose.versalistpro.presentation.ui.theme.secondary
import com.evenclose.versalistpro.presentation.ui.theme.white
import com.evenclose.versalistpro.presentation.viewmodel.ListViewModel

@Composable
fun ListScreen (
    navController: NavController,
    listId: Int,
    listViewModel: ListViewModel = hiltViewModel(),
) {

    /** Current List */
    val currentListData = listViewModel.currentListData.observeAsState(null)
    val currentInnerList = listViewModel.currentInnerList.observeAsState(null)

    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit, key2= showDialog) {
        listViewModel.getListData(listId)
        listViewModel.getCurrentInnerList(listId)
    }

    if (showDialog) {
        NewItemDialog(
            type = "DetailListItem",
            mainListId = listId,
            onDismissRequest = { showDialog = false })
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(primary)
            ) {

                /** HEADER */
                ListScreenHeader(
                    navController = navController,
                    listName = currentListData.value?.name ?: "Error"
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
        floatingActionButton = {
            FloatingActionButton(
                containerColor = secondary,
                contentColor = white,
                shape = RoundedCornerShape(50),
                onClick = {
                    showDialog = showDialog.not()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Add new Inner list item Icon",
                )

            }
        }
    ) { it ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {

            if (currentInnerList.value?.isNotEmpty() == true) {
                items(currentInnerList.value!!.size) {
                    Column() {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            InnerListItem(
                                innerListItem = currentInnerList.value!![it],
                            )
                        }
                    }
                    Divider(
                        color = white,
                        thickness = 1.dp
                    )
                }
            } else {
                /** PLACEHOLDER */
                item(1) {
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
    }
}