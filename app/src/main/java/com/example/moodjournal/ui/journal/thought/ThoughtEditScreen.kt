package com.example.moodjournal.ui.journal.thought

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodjournal.MoodJournalTopAppBar
import com.example.moodjournal.ui.AppViewModelProvider
import com.example.moodjournal.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object ThoughtEditDestination : NavigationDestination {
    override val route = "thought_edit"
    override val titleRes = "Edit thought"
    const val journalIdArg = "journalId"
    const val thoughtIdArg = "thoughtId"
    val routeWithArgs = "$route/{$journalIdArg}/{$thoughtIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ThoughtEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ThoughtEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            MoodJournalTopAppBar(title = "Edit thought", canNavigateBack = true, navigateUp = onNavigateUp)
        },
        modifier = modifier
    ) { innerPadding ->
        ThoughtEntryBody(
            thoughtUiState = viewModel.thoughtUiState,
            onThoughtValueChange = viewModel::updateUiState,
            onSaveClick = {
                  coroutineScope.launch {
                      viewModel.updateThought()
                      navigateBack()
                  }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}