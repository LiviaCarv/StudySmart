package com.project.studysmart.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.project.studysmart.R
import com.project.studysmart.domain.model.Subject
import com.project.studysmart.ui.theme.StudySmartTheme

@Composable
fun SubjectCard(
    modifier: Modifier = Modifier,
    subject: Subject
) {
    ElevatedCard(
        modifier = modifier
    ) {
        Image(painter = painterResource(R.drawable.img_books), contentDescription = "subject")
       Text(
           text = subject.name
       )
    }

}

@Preview
@Composable
private fun SubjectCardPrev() {
    StudySmartTheme {
        SubjectCard(subject = Subject("Math", 15.6f, listOf()))
    }
}