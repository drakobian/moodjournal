package com.example.moodjournal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodjournal.data.DataSource
import com.example.moodjournal.model.Emotion
import com.example.moodjournal.model.Journal
import com.example.moodjournal.model.Thought
import com.example.moodjournal.ui.EmotionScreen
import com.example.moodjournal.ui.JournalScreen
import com.example.moodjournal.ui.MoodJournalUiState
import com.example.moodjournal.ui.MoodJournalViewModel
import com.example.moodjournal.ui.ThoughtScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoodJournalApp() {
    val viewModel: MoodJournalViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value

    //// todo: hmmmmm. I may accept this at the moment
    // but also could be a fun lil' experiment to branch here
    // and see if I like using Navigation more :)))
    if (uiState.currentSelectedJournal == null) {
        MoodJournalList(
            journals = DataSource.journals,
            onJournalPressed = {
                viewModel.updateCurrentJournal(it)
            }
        )
    } else {
        MoodJournalScreen(
            uiState = uiState,
            onThoughtPressed = {
                viewModel.updateCurrentThought(it)
            },
            onEmotionPressed = {
                viewModel.updateCurrentEmotion(it)
            },
            onBackPressed = {
                viewModel.updateCurrentJournal(null)
            }
        )
    }
}

@Composable
fun MoodJournalList(
    journals: List<Journal>,
    onJournalPressed: (Journal?) -> Unit
) {
    LazyColumn(Modifier.fillMaxWidth()) {
        item {
            Text(
                text = "Journals",
                modifier = Modifier
                    .background(Color.LightGray)
                    .border(1.dp, Color.Black)
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }

        items(journals) {
            Box(Modifier.clickable {
                onJournalPressed(it)
            }) {
                Text(
                    text = it.date.toString(),
                    modifier = Modifier
                        .border(1.dp, Color.Black)
                        .padding(8.dp)
                        .fillMaxWidth(),
                )
            }
        }

        item {
            Text(
                text = "Add new journal",
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoodJournalScreen(
    uiState: MoodJournalUiState,
    onThoughtPressed: (Thought?) -> Unit,
    onEmotionPressed: (Emotion?) -> Unit,
    onBackPressed: () -> Unit,
    ) {
    if (uiState.currentSelectedThought == null && uiState.currentSelectedEmotion == null) {
        JournalScreen(
            // todo: shouldn't need to do this check here :)
            uiState.currentSelectedJournal!!,
            onThoughtPressed,
            onEmotionPressed,
            onBackPressed,
        )
    } else if (uiState.currentSelectedEmotion != null) {
        EmotionScreen(
            emotion = uiState.currentSelectedEmotion,
            onBackPressed = {
                onEmotionPressed(null)
            }
        )
    }
    else if (uiState.currentSelectedThought != null) {
        ThoughtScreen(
            thought = uiState.currentSelectedThought,
            onBackPressed = {
                onThoughtPressed(null)
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun MoodJournalAppPreview() {
    MoodJournalApp()
}