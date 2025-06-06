package com.project.studysmart.domain.repository

import com.project.studysmart.domain.model.Subject
import kotlinx.coroutines.flow.Flow

interface SubjectRepository {
    suspend fun upsertSubject(subject: Subject)

    fun getTotalSubjectCount(): Flow<Int>

    fun getTotalGoalHours(): Flow<Float>

    suspend fun getSubjectById(id: Int): Subject?

    suspend fun deleteSubject(subject: Subject)

    suspend fun getAllSubjects(): Flow<List<Subject>>
}