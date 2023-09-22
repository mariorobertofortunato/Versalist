package com.evenclose.versalistpro.presentation.screens.reminder

import android.widget.TimePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.PunchClock
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.evenclose.versalistpro.presentation.ui.theme.background
import com.evenclose.versalistpro.presentation.ui.theme.dark
import com.evenclose.versalistpro.presentation.ui.theme.light
import com.evenclose.versalistpro.presentation.ui.theme.primary
import com.evenclose.versalistpro.presentation.ui.theme.secondary
import com.evenclose.versalistpro.presentation.ui.theme.secondaryContainer
import com.evenclose.versalistpro.presentation.viewmodel.ListViewModel
import java.time.Instant
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderScreen(
    navController: NavController,
    listId: Int,
    listViewModel: ListViewModel = hiltViewModel(),
) {

    /** Dialog */
    var openDatePicker by remember { mutableStateOf(false) }
    var openTimePicker by remember { mutableStateOf(false) }

    val calendar = Calendar.getInstance()
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = Instant.now().toEpochMilli())
    val timePickerState = rememberTimePickerState(
        initialHour = calendar.get(Calendar.HOUR_OF_DAY),
        initialMinute = calendar.get(Calendar.MINUTE),
        //is24Hour = true
    )

    /** Data*/
    val currentListData = listViewModel.currentListData.observeAsState(null)

    listViewModel.getListData(listId)

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(secondary)
            ) {
                /** HEADER */
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "Arrow Back Icon",
                            tint = light
                        )
                    }
                    Text(
                        text = "Set Reminder",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        color = light,
                        overflow = TextOverflow.Ellipsis
                    )
                    // Placeholder icon
                    IconButton(
                        onClick = {
                            // TODO ?
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Menu,
                            contentDescription = "Menu Icon",
                            tint = secondary
                            // TODO cambiare colore nel caso la vogliamo utilizzare per aprire un menu
                        )
                    }
                }
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .padding(start = 2.dp, end = 2.dp, bottom = 2.dp)
                    .background(
                        color = primary,
                        shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                    )
            ) {
                FloatingActionButton(
                    containerColor = secondary,
                    contentColor = light,
                    shape = RoundedCornerShape(12.dp),
                    onClick = {
                        navController.navigateUp()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(12.dp)
                        .border(2.dp, primary, RoundedCornerShape(12.dp))
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Cancel",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = light,
                        )
                    }
                }

                FloatingActionButton(
                    containerColor = secondary,
                    contentColor = light,
                    shape = RoundedCornerShape(12.dp),
                    onClick = {
                        // TODO save reminder
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(12.dp)
                        .border(2.dp, primary, RoundedCornerShape(12.dp))
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Save",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = light,
                        )
                    }
                }
            }
        },
    ) {

        Column(
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .border(1.5.dp, dark, RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                .fillMaxSize()
                .padding(it)
                .background(primary)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = currentListData.value?.name ?: "",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                color = dark,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Column(
                modifier = Modifier
                    .border(
                        1.5.dp,
                        secondaryContainer.copy(alpha = 0.5f),
                        RoundedCornerShape(12.dp)
                    )
                    .background(light)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .clickable(
                            // Disable ripple effect because it sucks
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                                openDatePicker = true
                            },
                        )
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .background(color = background, shape = RoundedCornerShape(12.dp))
                                .border(2.dp, secondary, RoundedCornerShape(12.dp)),
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.CalendarMonth,
                                contentDescription = "Category Icon",
                                tint = dark,
                                modifier = Modifier
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(start = 8.dp)
                        ) {
                            Text(
                                text = "Date",
                                fontSize = 14.sp,
                                color = secondaryContainer,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = "TODO 23/3/2043",
                                fontSize = 20.sp,
                                color = dark,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = "Type Icon",
                        tint = dark,
                    )
                }

                Divider(
                    color = secondaryContainer.copy(alpha = 0.5f),
                    thickness = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        //.alpha(0.25f)
                        .align(CenterHorizontally)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .clickable(
                            // Disable ripple effect because it sucks
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                                openTimePicker = true
                            },
                        )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .background(color = background, shape = RoundedCornerShape(12.dp))
                                .border(2.dp, secondary, RoundedCornerShape(12.dp)),
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.PunchClock,
                                contentDescription = "Category Icon",
                                tint = dark,
                                modifier = Modifier
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(start = 8.dp)
                        ) {
                            Text(
                                text = "Time",
                                fontSize = 14.sp,
                                color = secondaryContainer,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = "TODO 12:34",
                                fontSize = 20.sp,
                                color = dark,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = "Type Icon",
                        tint = dark
                    )

                }
            }
        }
    }

    if (openDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                openDatePicker = false
            },
            confirmButton = {
                TextButton(onClick = {
                    /*TODO*/
                    openDatePicker = false
                }) {
                    Text(
                        text = "Done",
                        style = MaterialTheme.typography.headlineSmall,
                        color = light,
                        fontWeight = FontWeight.Bold,
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { openDatePicker = false }
                ) {
                    Text(
                        text = "Cancel",
                        style = MaterialTheme.typography.headlineSmall,
                        color = light,
                        fontWeight = FontWeight.Bold,
                    )
                }
            },
        ) {
            DatePicker(
                state = datePickerState,
                showModeToggle = false,
                dateValidator = { timestamp ->
                    timestamp > (Instant.now().toEpochMilli() - 8.64e+7)
                },
                colors = DatePickerDefaults.colors(
                    //containerColor = dark,
                    titleContentColor = light,
                    headlineContentColor = light,
                    weekdayContentColor = light,
                    yearContentColor = light,
                    currentYearContentColor = light,
                    selectedYearContentColor = light,
                    selectedYearContainerColor = secondaryContainer,
                    dayContentColor = light,
                    disabledDayContentColor = dark,
                    selectedDayContentColor = light,
                    selectedDayContainerColor = dark,
                    todayContentColor = light,
                    todayDateBorderColor = secondaryContainer,
                ),
            )
        }

    }

    if (openTimePicker) {
/*        TimePicker(
            state = timePickerState,
            colors = TimePickerDefaults.colors(
                clockDialColor = secondary,
                clockDialSelectedContentColor = secondary,
                clockDialUnselectedContentColor = light,
                selectorColor = light,
                periodSelectorBorderColor = secondary,
                periodSelectorSelectedContainerColor = secondary,
                periodSelectorUnselectedContainerColor = light,
                periodSelectorSelectedContentColor = light,
                periodSelectorUnselectedContentColor = secondaryContainer,
                timeSelectorSelectedContainerColor = secondary,
                timeSelectorUnselectedContainerColor = light,
                timeSelectorSelectedContentColor = light,
                timeSelectorUnselectedContentColor = secondaryContainer
            ),
            layoutType = TimePickerLayoutType.Horizontal
        )*/

    }
}
