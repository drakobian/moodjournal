package com.example.moodjournal.data.thought

import kotlinx.coroutines.flow.Flow

class OfflineThoughtRepository(private val thoughtDao: ThoughtDao): ThoughtRepository {
    override fun getThought(id: Int): Flow<Thought> = thoughtDao.getThought(id)

    override fun getAllThoughtsForJournal(journalId: Int): Flow<List<Thought>> = thoughtDao.getAllThoughtsForJournal(journalId)

    override suspend fun insert(emotion: Thought) = thoughtDao.insert(emotion)

    override suspend fun update(emotion: Thought) = thoughtDao.update(emotion)

    override suspend fun delete(emotion: Thought) = thoughtDao.delete(emotion)
}