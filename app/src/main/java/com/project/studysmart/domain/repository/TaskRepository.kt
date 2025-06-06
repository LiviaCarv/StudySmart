package com.project.studysmart.domain.repository

import com.project.studysmart.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun upsertTask(task: Task)

    suspend fun getTaskById(id: Int)

    suspend fun deleteTask(task: Task)

    suspend fun deleteTaskBySubjectId(subjectId: Int)

    fun getTasksForSubject(subjectId: Int): Flow<List<Task>>

    fun getAllTasks(): Flow<List<Task>>

}