package com.example.moodjournal.data.thought

import kotlinx.coroutines.flow.Flow

interface ThoughtRepository {
    fun getThought(id: Int): Flow<Thought>

    fun getAllThoughtsForJournal(journalId: Int): Flow<List<Thought>>

    suspend fun insert(thought: Thought)

    suspend fun update(thought: Thought)

    suspend fun delete(thought: Thought)
}