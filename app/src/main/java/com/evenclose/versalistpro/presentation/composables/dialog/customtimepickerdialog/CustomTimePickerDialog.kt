package com.evenclose.versalistpro.presentation.composables.dialog.customtimepickerdialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.evenclose.versalistpro.presentation.ui.theme.light
import com.evenclose.versalistpro.presentation.ui.theme.secondary
import com.evenclose.versalistpro.presentation.ui.theme.secondaryContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimePickerDialog(
    timePickerState: TimePickerState,
    onDismissRequest: () -> Unit,
) {


    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = true)
    ) {
        Column(
            horizontalAlignment = CenterHorizontally,
            modifier = Modifier
                .background(
                    color = secondaryContainer,
                    shape = RoundedCornerShape(32.dp)
                )
                .border(
                    width = 1.dp,
                    color = light,
                    shape = RoundedCornerShape(32.dp)
                )
                .padding(8.dp),
        ){
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Select Time",
                    style = MaterialTheme.typography.bodyMedium,
                    color = light,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
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
                )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                TextButton(
                    onClick = { onDismissRequest() }
                ) {
                    Text(
                        text = "Cancel",
                        style = MaterialTheme.typography.headlineSmall,
                        color = light,
                        fontWeight = FontWeight.Bold,
                    )
                }
                TextButton(onClick = {
                    onDismissRequest()
                }) {
                    Text(
                        text = "Done",
                        style = MaterialTheme.typography.headlineSmall,
                        color = light,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }

    }
}