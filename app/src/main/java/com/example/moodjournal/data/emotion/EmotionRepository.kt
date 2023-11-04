package com.example.moodjournal.data.emotion

import kotlinx.coroutines.flow.Flow

interface EmotionRepository {
    fun getEmotion(id: Int): Flow<Emotion>

    fun getAllEmotionsForJournal(journalId: Int): Flow<List<Emotion>>

    suspend fun insert(emotion: Emotion)

    suspend fun update(emotion: Emotion)

    suspend fun delete(emotion: Emotion)
}