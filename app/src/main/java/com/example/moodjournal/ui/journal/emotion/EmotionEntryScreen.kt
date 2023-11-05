package com.example.moodjournal.ui.journal.emotion

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

object EmotionEntryDestination : NavigationDestination {
    override val route = "emotion_entry"
    override val titleRes = "new emotion"
    const val journalIdArg = "journalId"
    val routeWithArgs = "$route/{$journalIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EmotionEntryScreen(
    navigateBack: () -> Unit,
    viewModel: EmotionEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
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
            value = viewModel.emotionUiState.emotionDetails.content,
            onValueChange = { viewModel.updateUiState(viewModel.emotionUiState.emotionDetails.copy(content = it)) },
            label = { Text("Emotions") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )

        OutlinedTextField(
            value = viewModel.emotionUiState.emotionDetails.nowPercent.toString(),
            onValueChange = { viewModel.updateUiState(viewModel.emotionUiState.emotionDetails.copy(nowPercent = getPercentageSafe(it))) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Now Percent") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )

        OutlinedTextField(
            value = viewModel.emotionUiState.emotionDetails.goalPercent.toString(),
            onValueChange = { viewModel.updateUiState(viewModel.emotionUiState.emotionDetails.copy(goalPercent = getPercentageSafe(it))) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Goal Percent") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )

        OutlinedTextField(
            value = viewModel.emotionUiState.emotionDetails.afterPercent.toString(),
            onValueChange = { viewModel.updateUiState(viewModel.emotionUiState.emotionDetails.copy(afterPercent = getPercentageSafe(it))) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("After Percent") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )

        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel.saveEmotion()
                    navigateBack()
                }
                      },
            enabled = viewModel.emotionUiState.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Save")
        }
    }
}