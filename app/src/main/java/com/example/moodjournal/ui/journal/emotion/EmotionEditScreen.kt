package com.example.moodjournal.ui.journal.emotion

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

object EmotionEditDestination : NavigationDestination {
    override val route = "emotion_edit"
    override val titleRes = "Edit emotion"
    const val journalIdArg = "journalId"
    const val emotionIdArg = "emotionId"
    val routeWithArgs = "$route/{$journalIdArg}/{$emotionIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EmotionEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EmotionEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            MoodJournalTopAppBar(title = "Edit emotion", canNavigateBack = true, navigateUp = onNavigateUp)
        },
        modifier = modifier
    ) { innerPadding ->
        EmotionEntryBody(
            emotionUiState = viewModel.emotionUiState,
            onEmotionValueChange = viewModel::updateUiState,
            onSaveClick = {
                  coroutineScope.launch {
                      viewModel.updateEmotion()
                      navigateBack()
                  }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}