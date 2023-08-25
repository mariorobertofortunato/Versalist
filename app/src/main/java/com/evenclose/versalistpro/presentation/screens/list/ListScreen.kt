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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.evenclose.versalistpro.presentation.composables.EmptyListPlaceholder
import com.evenclose.versalistpro.presentation.composables.InnerListItem
import com.evenclose.versalistpro.presentation.composables.NewItemDialog
import com.evenclose.versalistpro.presentation.ui.theme.inversePrimary
import com.evenclose.versalistpro.presentation.ui.theme.primary
import com.evenclose.versalistpro.presentation.ui.theme.secondary
import com.evenclose.versalistpro.presentation.ui.theme.white
import com.evenclose.versalistpro.presentation.viewmodel.ListViewModel

@Composable
fun ListScreen(
    navController: NavController,
    listId: Int,
    listViewModel: ListViewModel = hiltViewModel(),
) {

    /** Current List */
    val currentListData = listViewModel.currentListData.observeAsState(null)
    val currentInnerList = listViewModel.currentInnerList.observeAsState(null)

    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit, key2 = showDialog) {
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
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(
                containerColor = secondary,
                contentColor = white,
                shape = RoundedCornerShape(50),
                onClick = {
                    showDialog = showDialog.not()
                }
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(
                        text = "Add new item",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = white,
                    )
                }

            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {

            if (currentInnerList.value?.isNotEmpty() == true) {
                items(
                    items = currentInnerList.value!!,
                    /** For reasons beyond our understanding, this is the only way to make the key parameter work */
                    key = { item -> item }
                ) { item ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        InnerListItem(
                            innerListItem = item,
                        )
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