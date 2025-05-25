package com.project.studysmart.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.project.studysmart.R
import com.project.studysmart.ui.theme.StudySmartTheme

@Composable
fun DeleteDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String = "Delete Session?",
    bodyText: String
) {

    if (showDialog) {
        AlertDialog(
            title = {
                Text(
                    text = dialogTitle,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            text = {
                Text(text = bodyText)
            },
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmation()
                    },
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text("No")
                }
            }
        )
    }
}

@Preview
@Composable
private fun AddSubjectDialogPrev() {
    StudySmartTheme {
        DeleteDialog(
            showDialog = true,
            onDismissRequest = { TODO() },
            onConfirmation = { TODO() },
            bodyText = stringResource(R.string.confirm_delete_study_session)

        )
    }
}