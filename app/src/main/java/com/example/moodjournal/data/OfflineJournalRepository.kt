package com.example.moodjournal.data

import kotlinx.coroutines.flow.Flow

class OfflineJournalRepository(private val journalDao: JournalDao) : JournalRepository  {
    override fun getJournal(id: Int): Flow<Journal> = journalDao.getJournal(id)

    override fun getAllJournals(): Flow<List<Journal>> = journalDao.getAllJournals()

    override suspend fun insert(journal: Journal) = journalDao.insert(journal)

    override suspend fun update(journal: Journal) = journalDao.update(journal)

    override suspend fun delete(journal: Journal) = journalDao.delete(journal)
}