package com.project.studysmart.ui.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.project.studysmart.ui.theme.StudySmartTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(
    datePickerState: DatePickerState,
    showDatePicker: Boolean,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {

    if (showDatePicker) {

        DatePickerDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                    onDismiss()
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun DatePickerPrev() {
    StudySmartTheme {
        DatePicker(
            datePickerState = rememberDatePickerState(),
            showDatePicker = false,
            onDateSelected = {},
            onDismiss = {}
        )
    }
}