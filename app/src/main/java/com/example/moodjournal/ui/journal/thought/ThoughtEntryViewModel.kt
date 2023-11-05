package com.example.moodjournal.ui.journal.thought

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.moodjournal.data.thought.Thought
import com.example.moodjournal.data.thought.ThoughtRepository

@RequiresApi(Build.VERSION_CODES.O)
class ThoughtEntryViewModel(
    savedStateHandle: SavedStateHandle,
    private val thoughtRepository: ThoughtRepository
) : ViewModel() {
    private val journalId: Int = checkNotNull(savedStateHandle[ThoughtEntryDestination.journalIdArg])

    var thoughtUiState by mutableStateOf(ThoughtUiState())
        private set

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateUiState(thoughtDetails: ThoughtDetails) {
        thoughtUiState =
            ThoughtUiState(
                thoughtDetails = thoughtDetails,
                isEntryValid = validateInput(thoughtDetails)
            )
    }

    private fun validateInput(uiState: ThoughtDetails = thoughtUiState.thoughtDetails): Boolean {
        return with(uiState) {
            content.isNotBlank() && positiveThought.isNotBlank() && distortions.isNotBlank()
        }
    }

    suspend fun saveThought() {
        if (validateInput()) {
            thoughtRepository.insert(thoughtUiState.thoughtDetails.toThought(journalId))
        }
    }
}

fun ThoughtDetails.toThought(journalId: Int): Thought = Thought(
    id = id,
    journalId = journalId,
    content = content,
    afterPercent = afterPercent,
    nowPercent = nowPercent,
    beliefPercent = beliefPercent,
    distortions = distortions,
    positiveThought = positiveThought,
)

@RequiresApi(Build.VERSION_CODES.O)
data class ThoughtUiState(
    val thoughtDetails: ThoughtDetails = ThoughtDetails(),
    val isEntryValid: Boolean = false,
)

data class ThoughtDetails(
    val id: Int = 0,
    val content: String = "",
    val nowPercent: Int = 0,
    val afterPercent: Int = 0,
    val beliefPercent: Int = 0,
    val distortions: String = "",
    val positiveThought: String = "",
)

fun Thought.toThoughtDetails(): ThoughtDetails = ThoughtDetails(
    id = id,
    content = content,
    nowPercent = nowPercent,
    afterPercent = afterPercent,
    beliefPercent = beliefPercent,
    distortions = distortions,
    positiveThought = positiveThought,
)