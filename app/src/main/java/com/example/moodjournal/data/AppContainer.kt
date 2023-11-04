package com.example.moodjournal.data

import android.content.Context
import com.example.moodjournal.data.journal.JournalDatabase
import com.example.moodjournal.data.journal.JournalRepository
import com.example.moodjournal.data.journal.OfflineJournalRepository

interface AppContainer {
    val journalRepository: JournalRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val journalRepository: JournalRepository by lazy {
        OfflineJournalRepository(JournalDatabase.getDatabase(context).journalDao())
    }
}