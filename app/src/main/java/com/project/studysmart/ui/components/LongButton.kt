package com.project.studysmart.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.project.studysmart.ui.theme.StudySmartTheme

@Composable
fun LongButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    isEnabled: Boolean = true
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = { onClick() },
        enabled = isEnabled
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
private fun LongButtonPrev() {
    StudySmartTheme {
        LongButton(text = "Start study session", onClick = {})
    }
}