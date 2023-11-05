package com.example.moodjournal.ui.journal.thought

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
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
    viewModel: ThoughtEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

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
    Column {
        OutlinedTextField(
            value = viewModel.thoughtUiState.thoughtDetails.content,
            onValueChange = { viewModel.updateUiState(viewModel.thoughtUiState.thoughtDetails.copy(content = it)) },
            label = { Text("Thoughts") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )

        OutlinedTextField(
            value = viewModel.thoughtUiState.thoughtDetails.nowPercent.toString(),
            onValueChange = { viewModel.updateUiState(viewModel.thoughtUiState.thoughtDetails.copy(nowPercent = getPercentageSafe(it))) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Now Percent") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )

        OutlinedTextField(
            value = viewModel.thoughtUiState.thoughtDetails.afterPercent.toString(),
            onValueChange = { viewModel.updateUiState(viewModel.thoughtUiState.thoughtDetails.copy(afterPercent = getPercentageSafe(it))) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("After Percent") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )

        OutlinedTextField(
            value = viewModel.thoughtUiState.thoughtDetails.distortions,
            onValueChange = { viewModel.updateUiState(viewModel.thoughtUiState.thoughtDetails.copy(distortions = it)) },
            label = { Text("Distortions") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )

        OutlinedTextField(
            value = viewModel.thoughtUiState.thoughtDetails.positiveThought,
            onValueChange = { viewModel.updateUiState(viewModel.thoughtUiState.thoughtDetails.copy(positiveThought = it)) },
            label = { Text("Positive thought") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )

        OutlinedTextField(
            value = viewModel.thoughtUiState.thoughtDetails.beliefPercent.toString(),
            onValueChange = { viewModel.updateUiState(viewModel.thoughtUiState.thoughtDetails.copy(beliefPercent = getPercentageSafe(it))) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Belief Percent") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )

        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel.saveThought()
                    navigateBack()
                }
            },
            enabled = viewModel.thoughtUiState.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Save")
        }
    }
}