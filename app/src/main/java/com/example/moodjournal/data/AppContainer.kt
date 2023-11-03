package com.example.moodjournal.data

import android.content.Context

interface AppContainer {
    val journalRepository: JournalRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val journalRepository: JournalRepository by lazy {
        OfflineJournalRepository(JournalDatabase.getDatabase(context).journalDao())
    }
}