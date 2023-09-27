package com.evenclose.versalistpro.presentation.screens.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.evenclose.versalistpro.AlarmReceiver
import com.evenclose.versalistpro.presentation.composables.dialog.customdatepickerdialog.CustomDatePickerDialog
import com.evenclose.versalistpro.presentation.composables.dialog.customtimepickerdialog.CustomTimePickerDialog
import com.evenclose.versalistpro.presentation.composables.dialog.deletereminderdialog.DeleteReminderDialog
import com.evenclose.versalistpro.presentation.ui.theme.background
import com.evenclose.versalistpro.presentation.ui.theme.dark
import com.evenclose.versalistpro.presentation.ui.theme.light
import com.evenclose.versalistpro.presentation.ui.theme.primary
import com.evenclose.versalistpro.presentation.ui.theme.secondary
import com.evenclose.versalistpro.presentation.ui.theme.secondaryContainer
import com.evenclose.versalistpro.presentation.viewmodel.ListViewModel
import com.google.android.material.timepicker.TimeFormat
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.Calendar
import java.util.Locale
import kotlin.time.Duration.Companion.hours
import kotlin.time.DurationUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderScreen(
    navController: NavController,
    listId: Int,
    listViewModel: ListViewModel = hiltViewModel(),
) {

    val instant = Instant.now()
    val context = LocalContext.current

    /** Data*/
    val currentListData = listViewModel.currentListData.observeAsState(null)

    val datePickerState = if (currentListData.value?.reminderDate != null) {
        rememberDatePickerState(
            initialSelectedDateMillis = currentListData.value?.reminderDate!!.toEpochMilli()
        )
    } else {
        rememberDatePickerState(
            initialSelectedDateMillis = instant.toEpochMilli()
        )
    }

    val timePickerState = if (currentListData.value?.reminderDate != null) {
        rememberTimePickerState(
            initialHour = currentListData.value?.reminderDate!!.atZone(ZoneId.systemDefault()).hour,
            initialMinute = currentListData.value?.reminderDate!!.atZone(ZoneId.systemDefault()).minute,
        )
    } else {
        rememberTimePickerState(
            initialHour = instant.atZone(ZoneId.systemDefault()).hour,
            initialMinute = instant.atZone(ZoneId.systemDefault()).minute,
        )
    }


    /** Dialog */
    var openDatePicker by remember { mutableStateOf(false) }
    var openTimePicker by remember { mutableStateOf(false) }
    var openDeleteDialog by remember { mutableStateOf(false) }



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
                /** Back Btn */
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
                            text = "Back",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = light,
                        )
                    }
                }

                /** Save Btn */
                FloatingActionButton(
                    containerColor = secondary,
                    contentColor = light,
                    shape = RoundedCornerShape(12.dp),
                    onClick = {
                        val localDateTime = LocalDateTime.of(
                            Instant.ofEpochMilli(datePickerState.selectedDateMillis!!).atZone(ZoneId.systemDefault()).toLocalDate(),
                            LocalTime.of(timePickerState.hour, timePickerState.minute)
                        )
                        val instantReminder: Instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant()

                        // SET ALARM
                        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                        val intent = Intent(context, AlarmReceiver::class.java)
                        intent.putExtra("list_name", currentListData.value?.name)
                        intent.putExtra("list_id", currentListData.value?.id)
                        val pendingIntent = PendingIntent.getBroadcast(
                            context,
                            currentListData.value?.id ?: 0,
                            intent,
                            PendingIntent.FLAG_IMMUTABLE
                        )
                        alarmManager.setExact(
                            AlarmManager.RTC_WAKEUP,
                            instantReminder.toEpochMilli(),
                            pendingIntent
                        )

                        // SAVE TO DB
                        currentListData.value?.id?.let {
                            listViewModel.updateMainListReminder(
                                mainListItemId = it,
                                reminderDate = instantReminder
                            )
                        }
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
                /** DATE */
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
                                contentDescription = "date Icon",
                                tint = dark,
                                modifier = Modifier
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(start = 8.dp)
                        ) {
                            val dateFormat =
                                DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
                            val date = dateFormat.format(datePickerState.selectedDateMillis)
                            Text(
                                text = "Date",
                                fontSize = 14.sp,
                                color = secondaryContainer,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = date,
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
                        contentDescription = "Edit Icon",
                        tint = dark,
                    )
                }

                Divider(
                    color = secondaryContainer.copy(alpha = 0.5f),
                    thickness = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .align(CenterHorizontally)
                )

                /** TIME */
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
                                contentDescription = "Time Icon",
                                tint = dark,
                                modifier = Modifier
                            )
                        }
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(start = 8.dp)
                        ) {
                            val timeCalendar = Calendar.getInstance()
                            timeCalendar.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                            timeCalendar.set(Calendar.MINUTE, timePickerState.minute)
                            val timeFormat =
                                DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault())
                            val time = timeFormat.format(timeCalendar.time)

                            Text(
                                text = "Time",
                                fontSize = 14.sp,
                                color = secondaryContainer,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = time,
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
                        contentDescription = "Edit Icon",
                        tint = dark
                    )

                }
            }

            /** DELETE REMINDER BUTTON*/
            if (currentListData.value?.reminderDate != null) {
                FloatingActionButton(
                    containerColor = primary,
                    contentColor = dark,
                    shape = RoundedCornerShape(12.dp),
                    onClick = {
                        openDeleteDialog = true
                    },
                    modifier = Modifier
                        .padding(12.dp)
                        .border(
                            2.dp,
                            secondaryContainer.copy(alpha = 0.5f),
                            RoundedCornerShape(12.dp)
                        )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                    ) {
                        Text(
                            text = "Delete reminder",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = secondaryContainer,
                        )
                    }
                }

            }
        }
    }

    if (openDatePicker) {
        CustomDatePickerDialog(
            datePickerState = datePickerState,
            onDismissRequest = {
                openDatePicker = false
            }
        )
    }

    if (openTimePicker) {
        CustomTimePickerDialog(
            timePickerState = timePickerState,
            onDismissRequest = {
                openTimePicker = false
            }
        )
    }

    if (openDeleteDialog) {
        DeleteReminderDialog(
            mainListId = listId,
            onDismiss = {
                openDeleteDialog = false
            },
            navController = navController
        )
    }
}
