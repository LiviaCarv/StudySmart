package com.project.studysmart.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.studysmart.R
import com.project.studysmart.ui.theme.StudySmartTheme

@Composable
fun EmptyCardsContent(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int,
    emptyListText1: String = "",
    emptyListText2: String = ""
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(painter = painterResource(imageRes), contentDescription = "")
        Text(text = emptyListText1, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Text(text = emptyListText2, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
    }
}

@Preview(showBackground = true)
@Composable
private fun AddSubjectsCardPrev() {
    StudySmartTheme {
        EmptyCardsContent(
            imageRes = R.drawable.img_tasks,
            emptyListText1 = stringResource(R.string.no_tasks),
            emptyListText2 = stringResource(R.string.add_tasks)
        )
    }
}