package com.example.moodjournal.ui.journal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodjournal.MoodJournalTopAppBar
import com.example.moodjournal.ui.AppViewModelProvider
import com.example.moodjournal.ui.journal.emotion.EmotionsTable
import com.example.moodjournal.ui.journal.thought.ThoughtsTable
import com.example.moodjournal.ui.navigation.NavigationDestination
import java.time.format.DateTimeFormatter

object JournalDetailsDestination : NavigationDestination {
    override val route = "journal_details"
    override val titleRes = "journal details"
    const val journalIdArg = "journalId"
    val routeWithArgs = "$route/{$journalIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun JournalDetailsScreen(
    navigateToEditJournal: (Int) -> Unit,
    navigateBack: () -> Unit,
    navigateToEmotionEntry: (Int) -> () -> Unit,
    navigateToThoughtEntry: (Int) -> () -> Unit,
    navigateToEmotionEdit: (Int) -> (Int) -> Unit,
    navigateToThoughtEdit: (Int) -> (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: JournalDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            MoodJournalTopAppBar(
                title = uiState.value.journalDetails.date.format(DateTimeFormatter.ofPattern("MMMM dd, uuuu")),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }, floatingActionButton = {
            FloatingActionButton(onClick = { navigateToEditJournal(uiState.value.journalDetails.id) }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
        }, modifier = modifier
    ) { innerPadding ->
        Column(modifier.padding(innerPadding)) {
            Text(uiState.value.journalDetails.event)

            // todo: limit max height of these tables? and maybe of the text above too to make sure
            // everything stays on the screen nicely...
            EmotionsTable(
                emotions = uiState.value.emotionDetails,
                onEmotionPressed = navigateToEmotionEdit(uiState.value.journalDetails.id),
                navigateToEmotionEntry = navigateToEmotionEntry(uiState.value.journalDetails.id)
            )

            ThoughtsTable(
                thoughts = uiState.value.thoughtDetails,
                onThoughtPressed = navigateToThoughtEdit(uiState.value.journalDetails.id),
                navigateToThoughtEntry = navigateToThoughtEntry(uiState.value.journalDetails.id)
            )
        }
    }
}