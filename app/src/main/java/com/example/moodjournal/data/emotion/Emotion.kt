package com.example.moodjournal.data.emotion

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.moodjournal.data.journal.Journal

@Entity(tableName = "emotions",
    foreignKeys = [
        ForeignKey(
            entity = Journal::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("journalId"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE,
        )
    ])
data class Emotion(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val journalId: Int,
    val content: String,
    val nowPercent: Int,
    val goalPercent: Int,
    val afterPercent: Int,
)