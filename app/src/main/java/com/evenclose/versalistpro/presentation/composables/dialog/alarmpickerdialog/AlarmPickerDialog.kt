package com.evenclose.versalistpro.presentation.composables.dialog.alarmpickerdialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.evenclose.versalistpro.presentation.ui.theme.background
import com.evenclose.versalistpro.presentation.ui.theme.dark
import com.evenclose.versalistpro.presentation.ui.theme.light
import com.evenclose.versalistpro.presentation.ui.theme.secondary
import com.evenclose.versalistpro.presentation.ui.theme.secondaryContainer
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Calendar
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmPickerDialog(
    //mainListItem: MainListItem?,
    //listViewModel: ListViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {

    /** UI */
    val headerOptions = listOf("Date", "Time")
    val (selectedHeaderOption, onHeaderOptionSelected) = remember { mutableStateOf(headerOptions[0]) }


    /** DATE */
    val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.ROOT)
    val calendar = Calendar.getInstance()
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    )

    /** TIME */
    val timePickerState = rememberTimePickerState(
        initialHour = calendar.get(Calendar.HOUR_OF_DAY),
        initialMinute = calendar.get(Calendar.MINUTE),
        //is24Hour = true
    )

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {

        Box() {
            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier
                    .background(
                        color = secondaryContainer,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = light,
                        shape = RoundedCornerShape(12.dp)
                    )
                    //.size(550.dp)
                //.defaultMinSize(minHeight = 600.dp)
            ) {
                /** HEADER */
                Text(
                    text = "Set reminder",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 4.dp, start = 8.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall,
                    color = light,
                    fontWeight = FontWeight.Bold,
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .fillMaxWidth()
                        .background(light, RoundedCornerShape(12.dp))
                        .border(1.dp, secondaryContainer, RoundedCornerShape(12.dp))
                ) {
                    headerOptions.forEach { headerOption ->
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(4.dp)
                                .border(
                                    width = 1.dp,
                                    color = secondaryContainer,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .weight(1f)
                                .selectable(
                                    selected = (headerOption == selectedHeaderOption),
                                    onClick = { onHeaderOptionSelected(headerOption) }
                                )
                                .clickable(
                                    // Disable ripple effect because it sucks
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() },
                                    onClick = { onHeaderOptionSelected(headerOption) }
                                )
                                .background(
                                    color = if (headerOption == selectedHeaderOption) secondaryContainer else background,
                                    shape = RoundedCornerShape(12.dp)
                                )
                        ) {
                            Icon(
                                imageVector = if (headerOption == "Date") Icons.Outlined.CalendarMonth else Icons.Outlined.Schedule,
                                contentDescription = "Header option Icon",
                                tint = if (headerOption == selectedHeaderOption) light else secondaryContainer,
                                modifier = Modifier
                                    .padding(top = 8.dp, bottom = 8.dp, end = 4.dp)
                            )
                            Text(
                                text = headerOption,
                                color = if (headerOption == selectedHeaderOption) light else secondaryContainer,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
                Row(
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    if (selectedHeaderOption == "Date") {
                        DatePicker(
                            title = null,
                            state = datePickerState,
                            showModeToggle = false,
                            dateValidator = { timestamp ->
                                timestamp > (Instant.now().toEpochMilli() - 8.64e+7)
                            },
                            colors = DatePickerDefaults.colors(
                                containerColor = dark,
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
                    } else {
                        TimePicker(
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
                        )
                    }
                }

            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .align(BottomCenter)
            ) {
                DismissButton(onDismiss = onDismiss)
                OkButton()
            }
        }


    }
}

@Composable
fun OkButton() {
    TextButton(onClick = { /*TODO*/ }) {
        Text(
            text = "Done",
            style = MaterialTheme.typography.headlineSmall,
            color = light,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun DismissButton(
    onDismiss: () -> Unit
) {
    TextButton(onClick = { onDismiss() }
    ) {
        Text(
            text = "Cancel",
            style = MaterialTheme.typography.headlineSmall,
            color = light,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
fun AlarmPickerPreview() {
    AlarmPickerDialog() {

    }
}