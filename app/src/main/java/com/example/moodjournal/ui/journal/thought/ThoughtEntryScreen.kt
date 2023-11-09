package com.example.moodjournal.ui.journal.thought

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodjournal.MoodJournalTopAppBar
import com.example.moodjournal.ui.AppViewModelProvider
import com.example.moodjournal.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object ThoughtEntryDestination : NavigationDestination {
    override val route = "thought_entry"
    override val titleRes = "new thought"
    const val journalIdArg = "journalId"
    val routeWithArgs = "$route/{$journalIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ThoughtEntryScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    viewModel: ThoughtEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            MoodJournalTopAppBar(title = "Add thought", canNavigateBack = true, navigateUp = onNavigateUp)
        }
    )
    { innerPadding ->
        ThoughtEntryBody(
            thoughtUiState = viewModel.thoughtUiState,
            onThoughtValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveThought()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThoughtEntryBody(
    thoughtUiState: ThoughtUiState,
    onThoughtValueChange: (ThoughtDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier : Modifier = Modifier
) {
// todo: there's a better way to do this, and i must fine it
    val getPercentageSafe = { input: String ->
        when {
            input == "" -> 0
            input.toInt() < 0 -> 0
            input.toInt() > 100 -> 100
            input.toInt() >= 0 && input.toInt() <= 100 -> input.toInt()
            else -> 0
        }
    }

    // todo: make better :)
    Column(
        modifier = modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = thoughtUiState.thoughtDetails.content,
            onValueChange = { onThoughtValueChange(thoughtUiState.thoughtDetails.copy(content = it)) },
            label = { Text("Thoughts") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )

        OutlinedTextField(
            value = thoughtUiState.thoughtDetails.nowPercent.toString(),
            onValueChange = { onThoughtValueChange(thoughtUiState.thoughtDetails.copy(nowPercent = getPercentageSafe(it))) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Now Percent") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )

        OutlinedTextField(
            value = thoughtUiState.thoughtDetails.afterPercent.toString(),
            onValueChange = { onThoughtValueChange(thoughtUiState.thoughtDetails.copy(afterPercent = getPercentageSafe(it))) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("After Percent") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )

        OutlinedTextField(
            value = thoughtUiState.thoughtDetails.distortions,
            onValueChange = { onThoughtValueChange(thoughtUiState.thoughtDetails.copy(distortions = it)) },
            label = { Text("Distortions") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )

        OutlinedTextField(
            value = thoughtUiState.thoughtDetails.positiveThought,
            onValueChange = { onThoughtValueChange(thoughtUiState.thoughtDetails.copy(positiveThought = it)) },
            label = { Text("Positive thought") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )

        OutlinedTextField(
            value = thoughtUiState.thoughtDetails.beliefPercent.toString(),
            onValueChange = { onThoughtValueChange(thoughtUiState.thoughtDetails.copy(beliefPercent = getPercentageSafe(it))) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Belief Percent") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )

        Button(
            onClick = onSaveClick,
            enabled = thoughtUiState.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Save")
        }
    }
}