package com.project.studysmart.data.repository

import com.project.studysmart.data.local.SubjectDao
import com.project.studysmart.domain.model.Subject
import com.project.studysmart.domain.repository.SubjectRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(
    private val subjectDao: SubjectDao
) : SubjectRepository {

    override suspend fun upsertSubject(subject: Subject) {
        subjectDao.upsertSubject(subject)
    }

    override fun getTotalSubjectCount(): Flow<Int> {
        TODO("Not yet implemented")
    }

    override fun getTotalGoalHours(): Flow<Float> {
        TODO("Not yet implemented")
    }

    override suspend fun getSubjectById(id: Int): Subject? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSubject(subject: Subject) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllSubjects(): Flow<List<Subject>> {
        TODO("Not yet implemented")
    }
}