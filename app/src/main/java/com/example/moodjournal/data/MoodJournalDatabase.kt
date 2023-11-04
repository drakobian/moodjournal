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

@Database(entities = [Journal::class, Emotion::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MoodJournalDatabase : RoomDatabase() {
    abstract fun journalDao() : JournalDao
    abstract fun emotionDao() : EmotionDao

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