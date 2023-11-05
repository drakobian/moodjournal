package com.example.moodjournal.ui.journal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.moodjournal.data.journal.Journal
import com.example.moodjournal.data.journal.JournalRepository
import java.time.LocalDate

/**
 * ViewModel to validate and insert journal in the Room database.
 */
@RequiresApi(Build.VERSION_CODES.O)
class JournalEntryViewModel(private val journalRepository: JournalRepository) : ViewModel() {

    /**
     * Holds current journal ui state
     */
    var journalUiState by mutableStateOf(JournalUiState())
        private set

    /**
     * Updates the [journalUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun updateUiState(journalDetails: JournalDetails) {
        journalUiState =
            JournalUiState(journalDetails = journalDetails, isEntryValid = validateInput(journalDetails))
    }

    /**
     * Inserts an [Journal] in the Room database
     */
    suspend fun saveJournal() {
        if (validateInput()) {
            journalRepository.insert(journalUiState.journalDetails.toJournal())
        }
    }

    private fun validateInput(uiState: JournalDetails = journalUiState.journalDetails): Boolean {
        return with(uiState) {
            event.isNotBlank()
        }
    }
}

/**
 * Represents Ui State for an Journal.
 */
@RequiresApi(Build.VERSION_CODES.O)
data class JournalUiState(
    val journalDetails: JournalDetails = JournalDetails(),
    val isEntryValid: Boolean = false
)

@RequiresApi(Build.VERSION_CODES.O)
data class JournalDetails(
    val id: Int = 0,
    val event: String = "",
    val date: LocalDate = LocalDate.now(),
)

/**
 * Extension function to convert [JournalUiState] to [Journal].
 */
fun JournalDetails.toJournal(): Journal = Journal(
    id = id,
    event = event,
    date = date,
)

/**
 * Extension function to convert [Journal] to [JournalUiState]
 */
@RequiresApi(Build.VERSION_CODES.O)
fun Journal.toJournalUiState(isEntryValid: Boolean = false): JournalUiState = JournalUiState(
    journalDetails = this.toJournalDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Journal] to [JournalDetails]
 */
@RequiresApi(Build.VERSION_CODES.O)
fun Journal.toJournalDetails(): JournalDetails = JournalDetails(
    id = id,
    event = event,
    date = date,
)