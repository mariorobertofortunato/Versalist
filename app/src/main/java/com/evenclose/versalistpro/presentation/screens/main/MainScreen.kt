package com.evenclose.versalistpro.presentation.screens.main

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.evenclose.versalistpro.presentation.composables.EmptyListPlaceholder
import com.evenclose.versalistpro.presentation.composables.MainListItem
import com.evenclose.versalistpro.presentation.ui.theme.inversePrimary
import com.evenclose.versalistpro.presentation.ui.theme.primary
import com.evenclose.versalistpro.presentation.ui.theme.secondary
import com.evenclose.versalistpro.presentation.ui.theme.white
import com.evenclose.versalistpro.presentation.viewmodel.ListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    listViewModel: ListViewModel = hiltViewModel(),
) {
    /** All lists */
    val allLists = listViewModel.allLists.observeAsState(emptyList())

    LaunchedEffect(key1 = Unit) {
        listViewModel.fetchAllLists()
    }


    /*    LaunchedEffect(key1 = Unit) {
            userViewModel.getUserData()
        }
        LaunchedEffect(key1 = userData) {
            requestsViewModel.getUserRequestDocument()
        }
        LaunchedEffect(key1 = pendingRequests) {
            messagesViewModel.getActiveRooms()
        }
        LaunchedEffect(key1 = rooms) {
            messagesViewModel.fetchAllUserMessages()
        }*/


    Scaffold(
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
        floatingActionButton = {
            FloatingActionButton(
                containerColor = secondary,
                contentColor = white,
                shape = RoundedCornerShape(50),
                onClick = {
                    //navController.navigate(Screens.SearchScreen.route)
                    // TODO open dialog for new list
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "Add new list Icon",
                )

            }
        }
    ) { it ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {

            if (allLists.value?.isNotEmpty() == true) {
                items(allLists.value!!.size) {
                    Column() {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(
                                    horizontal = 12.dp,
                                    vertical = 6.dp
                                )
                                .fillMaxWidth()
                        ) {
                            MainListItem(
                                list = allLists.value!![it],
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
                            EmptyListPlaceholder(type = "mainScreenPlaceholder")
                        }
                    }
                }
            }
        }
    }
}