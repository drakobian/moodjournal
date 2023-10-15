package com.example.moodjournal.ui

import com.example.moodjournal.model.Emotion
import com.example.moodjournal.model.Journal
import com.example.moodjournal.model.Thought

data class MoodJournalUiState(
    val currentSelectedJournal: Journal? = null,
    val currentSelectedThought: Thought? = null,
    val currentSelectedEmotion: Emotion? = null,
)
