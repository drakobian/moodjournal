package com.example.moodjournal.ui.journal.emotion

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.moodjournal.data.emotion.Emotion
import com.example.moodjournal.data.emotion.EmotionRepository

@RequiresApi(Build.VERSION_CODES.O)
class EmotionEntryViewModel(
    savedStateHandle: SavedStateHandle,
    private val emotionRepository: EmotionRepository
) : ViewModel() {
    private val journalId: Int = checkNotNull(savedStateHandle[EmotionEntryDestination.journalIdArg])

    var emotionUiState by mutableStateOf(EmotionUiState())
        private set

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateUiState(emotionDetails: EmotionDetails) {
        emotionUiState =
            EmotionUiState(
                emotionDetails = emotionDetails,
                isEntryValid = validateInput(emotionDetails)
            )
    }

    private fun validateInput(uiState: EmotionDetails = emotionUiState.emotionDetails): Boolean {
        return with(uiState) {
            content.isNotBlank()
        }
    }

    suspend fun saveEmotion() {
        if (validateInput()) {
            emotionRepository.insert(emotionUiState.emotionDetails.toEmotion(journalId))
        }
    }
}

fun EmotionDetails.toEmotion(journalId: Int): Emotion = Emotion(
    id = id,
    journalId = journalId,
    content = content,
    afterPercent = afterPercent,
    goalPercent = goalPercent,
    nowPercent = nowPercent
)

@RequiresApi(Build.VERSION_CODES.O)
data class EmotionUiState(
    val emotionDetails: EmotionDetails = EmotionDetails(),
    val isEntryValid: Boolean = false,
)

data class EmotionDetails(
    val id: Int = 0,
    val content: String = "",
    val nowPercent: Int = 0,
    val goalPercent: Int = 0,
    val afterPercent: Int = 0,
)

fun Emotion.toEmotionDetails(): EmotionDetails = EmotionDetails(
    id = id,
    content = content,
    nowPercent = nowPercent,
    goalPercent = goalPercent,
    afterPercent = afterPercent,
)

@RequiresApi(Build.VERSION_CODES.O)
fun Emotion.toEmotionUiState(isEntryValid: Boolean = false): EmotionUiState = EmotionUiState(
    emotionDetails = this.toEmotionDetails(),
    isEntryValid = isEntryValid,
)