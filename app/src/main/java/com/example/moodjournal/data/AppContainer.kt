package com.example.moodjournal.data

import android.content.Context
import com.example.moodjournal.data.emotion.EmotionRepository
import com.example.moodjournal.data.emotion.OfflineEmotionRepository
import com.example.moodjournal.data.journal.JournalRepository
import com.example.moodjournal.data.journal.OfflineJournalRepository

interface AppContainer {
    val journalRepository: JournalRepository
    val emotionRepository: EmotionRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val journalRepository: JournalRepository by lazy {
        OfflineJournalRepository(MoodJournalDatabase.getDatabase(context).journalDao())
    }

    override val emotionRepository: EmotionRepository by lazy {
        OfflineEmotionRepository(MoodJournalDatabase.getDatabase(context).emotionDao())
    }
}