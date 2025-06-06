package com.project.studysmart.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "study-sessions")
data class Session(
    @PrimaryKey(autoGenerate = true)
    val sessionSubjectId: Int? = null,
    val relatedToSubject: String,
    val date: Long,
    val duration: Long,
    val sessionId: Int
)
