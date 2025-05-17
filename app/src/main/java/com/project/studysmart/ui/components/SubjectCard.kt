package com.project.studysmart.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.studysmart.R
import com.project.studysmart.domain.model.Subject
import com.project.studysmart.ui.theme.Blue
import com.project.studysmart.ui.theme.StudySmartTheme

@Composable
fun SubjectCard(
    modifier: Modifier = Modifier,
    subject: Subject,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(150.dp)
            .background(
                brush = Brush.verticalGradient(colors = subject.colors),
                shape = MaterialTheme.shapes.medium
            )
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.img_books),
                modifier = Modifier.size(80.dp),
                contentDescription = "subject"
            )
            Text(
                text = subject.name,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}

@Preview
@Composable
private fun SubjectCardPrev() {
    StudySmartTheme {
        SubjectCard(
            subject = Subject("kkkkkkkkkkkkkkk", 15.6f, listOf(Color.Red, Blue)),
            onClick = {})
    }
}