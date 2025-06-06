package com.project.studysmart.domain.repository

import com.project.studysmart.domain.model.Session
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    suspend fun insertSession(session: Session)

    suspend fun deleteSession(session: Session)

    fun getAllSessions(): Flow<List<Session>>

    suspend fun deleteSessionsBySubjectId(subjectId: Int)

    suspend fun getRecentSessionsForSubject(subjectId: Int)

    fun getTotalSessionsDuration(): Flow<Long>

    suspend fun getTotalSessionsDurationBySubjectId(subjectId: Int): Flow<Long>
}