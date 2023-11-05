package com.example.moodjournal.data

import android.content.Context
import com.example.moodjournal.data.emotion.EmotionRepository
import com.example.moodjournal.data.emotion.OfflineEmotionRepository
import com.example.moodjournal.data.journal.JournalRepository
import com.example.moodjournal.data.journal.OfflineJournalRepository
import com.example.moodjournal.data.thought.OfflineThoughtRepository
import com.example.moodjournal.data.thought.ThoughtRepository
import com.example.moodjournal.model.Thought

interface AppContainer {
    val journalRepository: JournalRepository
    val emotionRepository: EmotionRepository
    val thoughtRepository: ThoughtRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val journalRepository: JournalRepository by lazy {
        OfflineJournalRepository(MoodJournalDatabase.getDatabase(context).journalDao())
    }

    override val emotionRepository: EmotionRepository by lazy {
        OfflineEmotionRepository(MoodJournalDatabase.getDatabase(context).emotionDao())
    }

    override val thoughtRepository: ThoughtRepository by lazy {
        OfflineThoughtRepository(MoodJournalDatabase.getDatabase(context).thoughtDao())
    }
}