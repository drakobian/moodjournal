package com.example.moodjournal.ui

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moodjournal.data.DataSource
import com.example.moodjournal.model.Emotion
import com.example.moodjournal.model.Journal
import com.example.moodjournal.model.Thought

@Composable
fun JournalScreen(
    journal: Journal,
    onThoughtPressed: (Thought) -> Unit,
    onEmotionPressed: (Emotion) -> Unit,
    onBackPressed: () -> Unit,
) {
    BackHandler {
        onBackPressed()
    }
    Column {
        Text(text = "Date: ${journal.date.toString()}")
        Text(text = "Event: ${journal.event}")
        EmotionsTable(journal.emotions, onEmotionPressed)
        Spacer(modifier = Modifier.padding(bottom = 24.dp))
        ThoughtsTable(journal.thoughts, onThoughtPressed)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun JournalScreenPreview() {
    JournalScreen(DataSource.journal1, {}, {}, {})
}