package com.example.moodjournal.ui.journal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodjournal.MoodJournalTopAppBar
import com.example.moodjournal.data.emotion.Emotion
import com.example.moodjournal.ui.AppViewModelProvider
import com.example.moodjournal.ui.journal.emotion.EmotionsTable
import com.example.moodjournal.ui.navigation.NavigationDestination
import com.example.moodjournal.ui.theme.MoodJournalTheme
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object JournalEntryDestination : NavigationDestination {
    override val route = "journal_entry"
    override val titleRes = "journal entry"
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalEntryScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: JournalEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            MoodJournalTopAppBar(
                title = viewModel.journalUiState.journalDetails.date.format(DateTimeFormatter.ofPattern("MMMM dd, uuuu")),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        JournalEntryBody(
            journalUiState = viewModel.journalUiState,
            onJournalValueChange = viewModel::updateUiState,
            onSaveClick = {
                // Note: If the user rotates the screen very fast, the operation may get cancelled
                // and the journal may not be saved in the Database. This is because when config
                // change occurs, the Activity will be recreated and the rememberCoroutineScope will
                // be cancelled - since the scope is bound to composition.
                coroutineScope.launch {
                    viewModel.saveJournal()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                // todo: do something about this
                //.verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun JournalEntryBody(
    journalUiState: JournalUiState,
    onJournalValueChange: (JournalDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        JournalInputForm(
            journalDetails = journalUiState.journalDetails,
            onValueChange = onJournalValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        // todo: add Emotions table, which
        // means need to fetch Emotions attached to this
        // journal
        EmotionsTable(emotions = listOf(
            Emotion(1, 1, "sad, depressed", 99, 1, 5)
        ), onEmotionPressed = {})

        Button(
            onClick = onSaveClick,
            enabled = journalUiState.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Save")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JournalInputForm(
    journalDetails: JournalDetails,
    modifier: Modifier = Modifier,
    onValueChange: (JournalDetails) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = journalDetails.event,
            onValueChange = { onValueChange(journalDetails.copy(event = it)) },
            label = { Text("Event") },
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
//        OutlinedTextField(
//            value = journalDetails.price,
//            onValueChange = { onValueChange(journalDetails.copy(price = it)) },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
//            label = { Text(stringResource(R.string.journal_price_req)) },
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//            ),
//            leadingIcon = { Text(Currency.getInstance(Locale.getDefault()).symbol) },
//            modifier = Modifier.fillMaxWidth(),
//            enabled = enabled,
//            singleLine = true
//        )
//        if (enabled) {
//            Text(
//                text = "requried?",
//                modifier = Modifier.padding(start = 12.dp)
//            )
//        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun JournalEntryScreenPreview() {
    MoodJournalTheme {
        JournalEntryBody(journalUiState = JournalUiState(
            JournalDetails(
                event = "Event", date = LocalDate.now()
            )
        ), onJournalValueChange = {}, onSaveClick = {})
    }
}