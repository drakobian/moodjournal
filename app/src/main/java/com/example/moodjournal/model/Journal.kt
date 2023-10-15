package com.example.moodjournal.model

import java.time.LocalDate

data class Journal(
    val event: String,
    val date: LocalDate,
    val emotions: List<Emotion>,
    val thoughts: List<Thought>,
)
