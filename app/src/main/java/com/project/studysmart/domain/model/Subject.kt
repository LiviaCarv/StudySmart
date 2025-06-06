package com.project.studysmart.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.studysmart.ui.theme.gradient1
import com.project.studysmart.ui.theme.gradient2
import com.project.studysmart.ui.theme.gradient3
import com.project.studysmart.ui.theme.gradient4
import com.project.studysmart.ui.theme.gradient5

@Entity(tableName = "subjects")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val subjectId: Int? = null,
    val name: String,
    val goalHours: Float,
    val colors: List<Color>,
) {
    companion object {
        val subjectCardColors = listOf(gradient1, gradient2, gradient3, gradient4, gradient5)
    }
}
