package com.example.moodjournal.ui.journal.thought

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodjournal.data.thought.ThoughtRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class ThoughtEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val thoughtRepository: ThoughtRepository
) : ViewModel() {
    var thoughtUiState by mutableStateOf(ThoughtUiState())
        private set

    private val journalId: Int = checkNotNull(savedStateHandle[ThoughtEditDestination.journalIdArg])
    private val thoughtId: Int = checkNotNull(savedStateHandle[ThoughtEditDestination.thoughtIdArg])

    init {
        viewModelScope.launch {
            thoughtUiState = thoughtRepository.getThought(thoughtId)
                .filterNotNull()
                .first()
                .toThoughtUiState(true)
        }
    }

    suspend fun updateThought() {
        if (validateInput(thoughtUiState.thoughtDetails)) {
            thoughtRepository.update(thoughtUiState.thoughtDetails.toThought(journalId))
        }
    }

    fun updateUiState(thoughtDetails: ThoughtDetails) {
        thoughtUiState = ThoughtUiState(thoughtDetails = thoughtDetails, isEntryValid = validateInput(thoughtDetails))
    }

    private fun validateInput(uiState: ThoughtDetails = thoughtUiState.thoughtDetails): Boolean {
        return with(uiState) {
            content.isNotBlank() && positiveThought.isNotBlank() && distortions.isNotBlank()
        }
    }
}