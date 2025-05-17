package com.project.studysmart.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.studysmart.ui.theme.StudySmartTheme

@Composable
fun <T> Section(
    modifier: Modifier = Modifier,
    itemsList: List<T>,
    sectionTitle: String,
    icon: ImageVector? = null,
    itemContent: @Composable (T, () -> Unit) -> Unit,
    emptyListContent: @Composable () -> Unit,
    onItemClick: () -> Unit,
    onIconClick: () -> Unit,
) {
    Column(modifier) {
        SectionTitle(
            Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            sectionTitle,
            icon
        ) { onIconClick() }
        if (itemsList.isEmpty()) {
            emptyListContent()
        } else {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(itemsList)
                { item ->
                    itemContent(item, onItemClick)
                }
            }
        }
    }
}

@Preview
@Composable
private fun SectionPrev() {
    StudySmartTheme {
//        Section()
    }
}