package com.project.studysmart.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.studysmart.ui.theme.StudySmartTheme

@Composable
fun SectionTitle(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector? = null,
    onIconClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )
       if (icon != null) {
           IconButton(
               onClick = onIconClick
           ) {
               Icon(imageVector = icon, contentDescription = "")
           }
       }
    }

}

@Preview(showBackground = true)
@Composable
private fun SectionTitlePrev() {
    StudySmartTheme {
        SectionTitle(title = "Subjects", icon = Icons.Default.Add, onIconClick = {})
    }
}