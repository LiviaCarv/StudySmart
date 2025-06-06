package com.project.studysmart.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.project.studysmart.domain.model.Session
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {

    @Insert
    suspend fun insertSession(session: Session)

    @Delete
    suspend fun deleteSession(session: Session)

    @Query("SELECT * from `study-sessions`")
    fun getAllSessions() : Flow<List<Session>>

    @Query("DELETE FROM `study-sessions` WHERE sessionSubjectId = :subjectId")
    suspend fun deleteSessionsBySubjectId(subjectId: Int)

    @Query("SELECT * FROM `study-sessions` WHERE sessionSubjectId = :subjectId")
    suspend fun getRecentSessionsForSubject(subjectId: Int)

    @Query("SELECT SUM(duration) FROM `study-sessions`")
    fun getTotalSessionsDuration() : Flow<Long>

    @Query("SELECT SUM(duration) FROM `study-sessions` WHERE sessionSubjectId = :subjectId")
    suspend fun getTotalSessionsDurationBySubjectId(subjectId: Int) : Flow<Long>
}