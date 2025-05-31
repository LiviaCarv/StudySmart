package com.project.studysmart.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.studysmart.domain.model.Subject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectListBottomSheet(
    sheetState: SheetState,
    onDismissClick: () -> Unit,
    subjectList: List<Subject>,
    showBottomSheet: Boolean,
    bottomSheetTitle: String = "Related to subject",
    onSubjectClicked: (Subject) -> Unit

) {
    ModalBottomSheet(
        onDismissRequest = {
            onDismissClick()
        },
        sheetState = sheetState,
        dragHandle = {
            BottomSheetDefaults.DragHandle()
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            contentPadding = PaddingValues(10.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            items(subjectList) { subject ->
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { onSubjectClicked(subject) }
                ) {
                    Text(
                        text = subject.name,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}


