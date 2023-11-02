package com.example.moodjournal.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodjournal.data.DataSource
import com.example.moodjournal.model.Journal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@RequiresApi(Build.VERSION_CODES.O)
class HomeViewModel : ViewModel() {
    // todo: add repo and data stuff
    val homeUiState: StateFlow<HomeUiState> = flowOf(DataSource.journals).map{ HomeUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
    )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

@RequiresApi(Build.VERSION_CODES.O)
data class HomeUiState(val journalList: List<Journal> = listOf())