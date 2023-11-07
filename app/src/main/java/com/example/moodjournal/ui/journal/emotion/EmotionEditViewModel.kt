package com.example.moodjournal.ui.journal.emotion

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodjournal.data.emotion.EmotionRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class EmotionEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val emotionRepository: EmotionRepository
) : ViewModel() {
    var emotionUiState by mutableStateOf(EmotionUiState())
        private set

    private val journalId: Int = checkNotNull(savedStateHandle[EmotionEditDestination.journalIdArg])
    private val emotionId: Int = checkNotNull(savedStateHandle[EmotionEditDestination.emotionIdArg])

    init {
        viewModelScope.launch {
            emotionUiState = emotionRepository.getEmotion(emotionId)
                .filterNotNull()
                .first()
                .toEmotionUiState(true)
        }
    }

    suspend fun updateEmotion() {
        if (validateInput(emotionUiState.emotionDetails)) {
            emotionRepository.update(emotionUiState.emotionDetails.toEmotion(journalId))
        }
    }

    fun updateUiState(emotionDetails: EmotionDetails) {
        emotionUiState = EmotionUiState(emotionDetails = emotionDetails, isEntryValid = validateInput(emotionDetails))
    }

    private fun validateInput(uiState: EmotionDetails = emotionUiState.emotionDetails): Boolean {
        return with(uiState) {
            content.isNotBlank()
        }
    }
}