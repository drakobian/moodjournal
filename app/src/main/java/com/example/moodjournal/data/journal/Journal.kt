package com.example.moodjournal.data.journal

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDate

@Entity(tableName = "journals")
data class Journal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val event: String,
    val date: LocalDate,
)
