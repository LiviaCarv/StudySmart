package com.project.studysmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.project.studysmart.domain.model.Session
import com.project.studysmart.domain.model.Subject
import com.project.studysmart.domain.model.Task
import com.project.studysmart.ui.NavGraphs
import com.project.studysmart.ui.theme.StudySmartTheme
import com.project.studysmart.ui.theme.gradient1
import com.project.studysmart.ui.theme.gradient5
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudySmartTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
val subjectList = listOf(
    Subject(1, "Math", 3f, gradient1),
    Subject(2, "Portuguese", 7.0f, gradient5),
    Subject(3, "Geo", 5.0f, gradient1),
    Subject(4, "Physics", 3f, gradient1)
)

val taskList = listOf(
    Task(1, 2, "Prepare notes", "need to study", 4L, 0, "", true),
    Task(1, 2, "Watch next lesson", "need to study", 4L, 1, "", false),
    Task(1, 2, "Study 2 hrs", "need to study", 4L, 2, "", false)
)

val sessionList = listOf(
    Session(sessionSubjectId = 1, relatedToSubject = "English", 1234L, 2L, 0),
    Session(sessionSubjectId = 2, relatedToSubject = "Portuguese", 1234L, 2L, 1),
    Session(sessionSubjectId = 3, relatedToSubject = "Maths", 1234L, 2L, 2),
    Session(sessionSubjectId = 4, relatedToSubject = "Physics", 1234L, 2L, 3),
)


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StudySmartTheme {

    }
}