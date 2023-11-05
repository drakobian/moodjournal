package com.example.moodjournal.ui.journal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodjournal.data.journal.JournalRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class JournalEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val journalRepository: JournalRepository
) : ViewModel() {

    var journalUiState by mutableStateOf(JournalUiState())
        private set

    private val journalId: Int = checkNotNull(savedStateHandle[JournalEditDestination.journalIdArg])

    init {
        viewModelScope.launch {
            journalUiState = journalRepository.getJournal(journalId)
                .filterNotNull()
                .first()
                .toJournalUiState(true)
        }
    }

    suspend fun updateJournal() {
        if (validateInput(journalUiState.journalDetails)) {
            journalRepository.update(journalUiState.journalDetails.toJournal())
        }
    }

    fun updateUiState(journalDetails: JournalDetails) {
        journalUiState = JournalUiState(journalDetails = journalDetails, isEntryValid = validateInput(journalDetails))
    }

    private fun validateInput(uiState: JournalDetails = journalUiState.journalDetails): Boolean {
        return with(uiState) {
            event.isNotBlank()
        }
    }
}