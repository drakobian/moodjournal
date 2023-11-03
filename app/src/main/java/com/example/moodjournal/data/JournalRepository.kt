package com.example.moodjournal.data

import kotlinx.coroutines.flow.Flow

interface JournalRepository {
    fun getJournal(id: Int): Flow<Journal>

    fun getAllJournals(): Flow<List<Journal>>

    suspend fun insert(journal: Journal)

    suspend fun update(journal: Journal)

    suspend fun delete(journal: Journal)
}