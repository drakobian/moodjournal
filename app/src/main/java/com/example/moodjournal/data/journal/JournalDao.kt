package com.example.moodjournal.data.journal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface JournalDao {
    @Query("SELECT * FROM journals WHERE id = :id")
    fun getJournal(id: Int): Flow<Journal>

    @Query("SELECT * FROM journals ORDER BY date DESC")
    fun getAllJournals(): Flow<List<Journal>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(journal: Journal)

    @Update
    suspend fun update(journal: Journal)

    @Delete
    suspend fun delete(journal: Journal)
}