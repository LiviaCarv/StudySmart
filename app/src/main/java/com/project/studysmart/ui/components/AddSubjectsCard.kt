package com.project.studysmart.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.project.studysmart.R
import com.project.studysmart.ui.theme.StudySmartTheme

@Composable
fun AddSubjectsCard(
    modifier: Modifier = Modifier,
    emptyListText1: String = "You don't have any subjects.",
    emptyListText2: String = "Click the + button to add new subject."
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(R.drawable.img_books), contentDescription = "")
        Text(text = emptyListText1, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Text(text = emptyListText2, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
    }
}

@Preview(showBackground = true)
@Composable
private fun AddSubjectsCardPrev() {
    StudySmartTheme {
        AddSubjectsCard()
    }
}