package com.project.studysmart.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.studysmart.domain.model.Subject
import com.project.studysmart.ui.theme.StudySmartTheme

@Composable
fun AddSubjectDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String = "Add/Update Subject",
    selectedColor: List<Color>,
    onColorChange: (List<Color>) -> Unit,
    subject: String,
    goalStudyHours: String,
    onSubjectNameChange: (String) -> Unit,
    onGoalStudyHoursChange: (String) -> Unit
) {
    var subjectNameError by rememberSaveable { mutableStateOf<String?>(null) }
    var goalHoursError by rememberSaveable { mutableStateOf<String?>(null) }

    subjectNameError = when {
        subject.isBlank() -> "Please enter subject name"
        subject.length < 3 -> "Subject name is too short."
        subject.length > 20 -> "Subject name is too long."
        else -> null
    }

    goalHoursError = when {
        goalStudyHours.isBlank() -> "Please enter goal study hours."
        goalStudyHours.toFloatOrNull() == null -> "Not valid number."
        goalStudyHours.toFloat() < 1f -> "Please set at least 1 hour."
        goalStudyHours.toFloat() > 100f -> "Please set maximum of 1000 hours."
        else -> null
    }
    if (showDialog) {
        AlertDialog(
            title = {
                Text(
                    text = dialogTitle,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Subject.subjectCardColors.forEach { colors ->

                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(brush = Brush.verticalGradient(colors))
                                    .clickable { onColorChange(colors) }
                                    .border(
                                        width = 1.dp,
                                        shape = CircleShape,
                                        color =
                                        if (colors == selectedColor) MaterialTheme.colorScheme.onSurface else Color.Transparent
                                    )
                            )
                        }
                    }
                    OutlinedTextField(
                        value = subject,
                        onValueChange = onSubjectNameChange,
                        label = { Text("Subject Name") },
                        singleLine = true,
                        supportingText = { Text(text = subjectNameError.orEmpty()) },
                        isError = subjectNameError != null && subject.isNotBlank()
                    )
                    OutlinedTextField(
                        value = goalStudyHours,
                        onValueChange = onGoalStudyHoursChange,
                        label = { Text("Goal Study Hours") },
                        singleLine = true,
                        supportingText = { Text(text = goalHoursError.orEmpty()) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = goalHoursError != null && goalStudyHours.isNotBlank()

                    )
                }
            },
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmation()
                    },
                    enabled = goalHoursError.isNullOrEmpty() && subjectNameError.isNullOrEmpty()
                ) {
                    Text("Save")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Preview
@Composable
private fun AddSubjectDialogPrev() {
    StudySmartTheme {
        AddSubjectDialog(
            showDialog = true,
            onDismissRequest = { TODO() },
            onConfirmation = { TODO() },
            selectedColor = Subject.subjectCardColors[1],
            onColorChange = { TODO() },
            subject = "math",
            goalStudyHours = "3.5",
            onSubjectNameChange = { TODO() },
            onGoalStudyHoursChange = { TODO() }
        )
    }
}