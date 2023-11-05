package com.example.moodjournal.ui.journal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodjournal.MoodJournalTopAppBar
import com.example.moodjournal.ui.AppViewModelProvider
import com.example.moodjournal.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object JournalEditDestination : NavigationDestination {
    override val route = "journal_edit"
    override val titleRes = "Edit journal"
    const val journalIdArg = "journalId"
    val routeWithArgs = "$route/{$journalIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun JournalEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: JournalEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            MoodJournalTopAppBar(title = "Edit journal", canNavigateBack = true, navigateUp = onNavigateUp)
        },
        modifier = modifier
    ) { innerPadding ->
        JournalEntryBody(
            journalUiState = viewModel.journalUiState,
            onJournalValueChange = viewModel::updateUiState,
            onSaveClick = {
                  coroutineScope.launch {
                    viewModel.updateJournal()
                    navigateBack()
                  }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}