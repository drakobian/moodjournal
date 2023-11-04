package com.example.moodjournal.data.emotion

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface EmotionDao {
    @Query("SELECT * FROM emotions WHERE id = :id")
    fun getEmotion(id: Int): Flow<Emotion>

    @Query("SELECT * FROM emotions WHERE journalId = :journalId")
    fun getAllEmotionsForJournal(journalId: Int): Flow<List<Emotion>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(emotion: Emotion)

    @Update
    suspend fun update(emotion: Emotion)

    @Delete
    suspend fun delete(emotion: Emotion)
}