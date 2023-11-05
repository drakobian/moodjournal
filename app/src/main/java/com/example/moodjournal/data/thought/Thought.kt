package com.example.moodjournal.data.thought

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.moodjournal.data.journal.Journal

@Entity(tableName = "thoughts",
    foreignKeys = [
        ForeignKey(
            entity = Journal::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("journalId"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE,
        )
    ])
data class Thought(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val journalId: Int,
    val content: String,
    val nowPercent: Int,
    val afterPercent: Int,
    val distortions: String,
    val positiveThought: String,
    val beliefPercent: Int,
)