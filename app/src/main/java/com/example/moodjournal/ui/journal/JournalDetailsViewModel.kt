package com.example.moodjournal.ui.journal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodjournal.data.emotion.Emotion
import com.example.moodjournal.data.emotion.EmotionRepository
import com.example.moodjournal.data.emotion.OfflineEmotionRepository
import com.example.moodjournal.data.journal.JournalRepository
import com.example.moodjournal.ui.journal.emotion.EmotionDetails
import com.example.moodjournal.ui.journal.emotion.toEmotionDetails
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class JournalDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val journalRepository: JournalRepository,
    private val emotionRepository: EmotionRepository,
) : ViewModel() {

    private val journalId: Int = checkNotNull(savedStateHandle[JournalDetailsDestination.journalIdArg])

    // todo: I think here's where I'll slap on the Emotions and Thoughts tables??? hmm :) 
    @RequiresApi(Build.VERSION_CODES.O)
    val uiState: StateFlow<JournalDetailsUiState> =
        journalRepository.getJournal(journalId)
            .filterNotNull()
            .combine(emotionRepository.getAllEmotionsForJournal(journalId))
            { journal, emotions ->
                JournalDetailsUiState(
                    journalDetails = journal.toJournalDetails(),
                    emotionDetails = emotions.map { e -> e.toEmotionDetails() }
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = JournalDetailsUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

@RequiresApi(Build.VERSION_CODES.O)
data class JournalDetailsUiState(
    val journalDetails: JournalDetails = JournalDetails(),
    val emotionDetails: List<EmotionDetails> = listOf(),
)