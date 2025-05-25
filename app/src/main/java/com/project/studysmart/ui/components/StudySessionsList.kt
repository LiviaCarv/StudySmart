package com.project.studysmart.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.studysmart.R
import com.project.studysmart.domain.model.Session
import com.project.studysmart.ui.theme.StudySmartTheme

@Composable
fun StudySessionsSection(
    modifier: Modifier = Modifier,
    sessionsList: List<Session>,
    onDeleteIconClick: (Session) -> Unit
) {
    Column(modifier) {
        SectionTitle(
            Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            "RECENT STUDY SESSIONS",
            null,
            {})
        if (sessionsList.isEmpty()) {
            EmptyCardsContent(
                imageRes = R.drawable.img_lamp,
                emptyListText1 = stringResource(R.string.no_study_sessions),
                emptyListText2 = stringResource(R.string.add_study_sessions)
            )
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                sessionsList.forEach { session ->
                    SessionCard(
                        session = session,
                        onDeleteIconClick = { onDeleteIconClick(session) }
                    )
                }
            }
        }
    }
}

@Composable
fun SessionCard(
    modifier: Modifier = Modifier,
    session: Session,
    onDeleteIconClick: (Session) -> Unit,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .align(Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier,
            ) {
                Text(
                    text = session.relatedToSubject,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "${session.date}",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = "${session.duration} hr",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray
                )
                IconButton(onClick = { onDeleteIconClick(session) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.delete_study_session)
                    )
                }

            }
        }

    }
}

@Preview
@Composable
private fun TaskCardPrev() {
    StudySmartTheme {
        SessionCard(
            session = Session(1, "Math", 1L, 2L, 0),
            onDeleteIconClick = { },
        )
    }
}