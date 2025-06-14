package com.project.studysmart.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.studysmart.R
import com.project.studysmart.domain.model.Task
import com.project.studysmart.ui.theme.StudySmartTheme
import com.project.studysmart.util.Priority

@Composable
fun TasksSection(
    modifier: Modifier = Modifier,
    taskList: List<Task>,
    onCheckBoxClick: (task: Task) -> Unit,
    onTaskClick: (Int?) -> Unit,
    title: String,
    emptyListText1: String,
    emptyListText2: String
) {
    Column(modifier) {
        SectionTitle(
            Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            title,
            null,
            {})
        if (taskList.isEmpty()) {
            EmptyCardsContent(
                imageRes = R.drawable.img_tasks,
                emptyListText1 = emptyListText1,
                emptyListText2 = emptyListText2
            )
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                taskList.forEach { task ->
                    TaskCard(
                        task = task,
                        onTaskClick = { onTaskClick(task.taskId) },
                        onCheckBoxClick = { onCheckBoxClick(task) }
                    )
                }
            }
        }
    }
}

@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    task: Task,
    onTaskClick: (Int?) -> Unit,
    onCheckBoxClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onTaskClick(task.taskId) }
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TaskCheckBox(
                task.isComplete,
                Priority.fromInt(task.priority).color
            ) { onCheckBoxClick() }
            Column(
                modifier = Modifier,
            ) {
                Text(
                    text = task.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (task.isComplete) {
                        TextDecoration.LineThrough
                    } else TextDecoration.None
                )
                Text(
                    text = "${task.dueDate}",
                    style = MaterialTheme.typography.bodySmall,

                    )
            }
        }

    }
}

@Preview
@Composable
private fun TaskCardPrev() {
    StudySmartTheme {
        TaskCard(
            task = Task(1, 2, "need to do", "", 4L, 3, "mat", true),
            onTaskClick = { },
            onCheckBoxClick = {}
        )
    }
}