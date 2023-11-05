package com.example.moodjournal.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moodjournal.data.emotion.Emotion
import com.example.moodjournal.data.emotion.EmotionDao
import com.example.moodjournal.data.journal.Journal
import com.example.moodjournal.data.journal.JournalDao
import com.example.moodjournal.data.thought.Thought
import com.example.moodjournal.data.thought.ThoughtDao

@Database(entities = [Journal::class, Emotion::class, Thought::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MoodJournalDatabase : RoomDatabase() {
    abstract fun journalDao() : JournalDao
    abstract fun emotionDao() : EmotionDao
    abstract fun thoughtDao() : ThoughtDao

    companion object {
        @Volatile
        private var Instance: MoodJournalDatabase? = null

        fun getDatabase(context: Context): MoodJournalDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MoodJournalDatabase::class.java, "journal_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}