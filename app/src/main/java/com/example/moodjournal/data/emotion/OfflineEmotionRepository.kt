package com.example.moodjournal.data.emotion

import kotlinx.coroutines.flow.Flow

class OfflineEmotionRepository(private val emotionDao: EmotionDao): EmotionRepository {
    override fun getEmotion(id: Int): Flow<Emotion> = emotionDao.getEmotion(id)

    override fun getAllEmotionsForJournal(journalId: Int): Flow<List<Emotion>> = emotionDao.getAllEmotionsForJournal(journalId)

    override suspend fun insert(emotion: Emotion) = emotionDao.insert(emotion)

    override suspend fun update(emotion: Emotion) = emotionDao.update(emotion)

    override suspend fun delete(emotion: Emotion) = emotionDao.delete(emotion)
}