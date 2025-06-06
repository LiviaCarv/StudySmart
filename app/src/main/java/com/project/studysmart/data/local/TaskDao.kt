package com.project.studysmart.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.project.studysmart.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Upsert
    suspend fun upsertTask(task: Task)

    @Query("SELECT * from tasks WHERE taskId = :id")
    suspend fun getTaskById(id: Int)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM TASKS WHERE taskSubjectId = :subjectId")
    suspend fun deleteTaskBySubjectId(subjectId: Int)

    @Query("SELECT * FROM TASKS WHERE taskSubjectId = :subjectId")
    fun getTasksForSubject(subjectId: Int) : Flow<List<Task>>

    @Query("SELECT * FROM TASKS")
    fun getAllTasks(): Flow<List<Task>>

}