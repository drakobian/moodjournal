package com.example.moodjournal.data.thought

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ThoughtDao {
    @Query("SELECT * FROM thoughts WHERE id = :id")
    fun getThought(id: Int): Flow<Thought>

    @Query("SELECT * FROM thoughts WHERE journalId = :journalId")
    fun getAllThoughtsForJournal(journalId: Int): Flow<List<Thought>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(emotion: Thought)

    @Update
    suspend fun update(emotion: Thought)

    @Delete
    suspend fun delete(emotion: Thought)
}