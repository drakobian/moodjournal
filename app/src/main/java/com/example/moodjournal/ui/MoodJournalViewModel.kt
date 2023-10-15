package com.example.moodjournal.ui

import androidx.lifecycle.ViewModel
import com.example.moodjournal.model.Emotion
import com.example.moodjournal.model.Journal
import com.example.moodjournal.model.Thought
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MoodJournalViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MoodJournalUiState())
    val uiState: StateFlow<MoodJournalUiState> = _uiState

    fun updateCurrentThought(thought: Thought?) {
        _uiState.update {
            it.copy(
                currentSelectedThought = thought
            )
        }
    }

    fun updateCurrentEmotion(emotion: Emotion?) {
        _uiState.update {
            it.copy(
                currentSelectedEmotion = emotion
            )
        }
    }

    fun updateCurrentJournal(journal: Journal?) {
        _uiState.update {
            it.copy(
                currentSelectedJournal = journal
            )
        }
    }
}