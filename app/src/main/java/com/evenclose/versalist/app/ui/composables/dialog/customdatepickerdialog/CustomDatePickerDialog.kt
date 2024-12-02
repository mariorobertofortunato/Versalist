package com.evenclose.versalist.app.ui.composables.dialog.customdatepickerdialog

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.evenclose.versalist.app.ui.theme.dark
import com.evenclose.versalist.app.ui.theme.light
import com.evenclose.versalist.app.ui.theme.secondaryContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerDialog(
    datePickerState: DatePickerState,
    onDismissRequest: () -> Unit,
) {

    DatePickerDialog(
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
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
        },
        dismissButton = {
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
        },
        modifier = Modifier
            .border(
                width = 1.dp,
                color = light,
                shape = RoundedCornerShape(30.dp)
            ),
        colors = DatePickerDefaults.colors(
            containerColor = secondaryContainer,
        )

    ) {
        DatePicker(
            state = datePickerState,
            showModeToggle = false,
/*            dateValidator = { timestamp ->
                timestamp > (Instant.now().toEpochMilli() - 8.64e+7)
            },*/
            colors = DatePickerDefaults.colors(
                titleContentColor = light,
                headlineContentColor = light,
                weekdayContentColor = light,
                yearContentColor = light,
                currentYearContentColor = light,
                selectedYearContentColor = light,
                selectedYearContainerColor = dark,
                dayContentColor = light,
                disabledDayContentColor = dark,
                selectedDayContentColor = light,
                selectedDayContainerColor = dark,
                todayContentColor = light,
                todayDateBorderColor = dark,
            ),
        )
    }
}