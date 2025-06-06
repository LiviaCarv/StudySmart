package com.project.studysmart.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.project.studysmart.domain.model.Subject
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {

    @Upsert
    suspend fun upsertSubject(subject: Subject)

    @Query("SELECT COUNT(*) FROM SUBJECTS")
    fun getTotalSubjectCount(): Flow<Int>

    @Query("SELECT SUM(goalHours) FROM SUBJECTS")
    fun getTotalGoalHours(): Flow<Float>

    @Query("SELECT * FROM SUBJECTS WHERE subjectId = :id")
    suspend fun getSubjectById(id: Int): Subject?

    @Delete
    suspend fun deleteSubject(subject: Subject)

    @Query("SELECT * FROM SUBJECTS")
    suspend fun getAllSubjects(): Flow<List<Subject>>
}