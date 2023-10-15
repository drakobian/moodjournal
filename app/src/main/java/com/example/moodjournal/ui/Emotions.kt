package com.example.moodjournal.ui

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moodjournal.data.DataSource
import com.example.moodjournal.model.Emotion

@Composable
fun EmotionScreen(emotion: Emotion, onBackPressed: () -> Unit) {
    BackHandler {
        onBackPressed()
    }

    Column {
        Text(
            text = "Emotions: ${emotion.content.joinToString(", ")}"
        )

        Row {
            Text(text = "Now: ${emotion.nowPercent}%")
            Text(text = "   Goal: ${emotion.goalPercent}%")
            Text(text = "   After: ${emotion.afterPercent}%")
        }
    }
}

@Preview
@Composable
fun EmotionScreenPreview() {
    EmotionScreen(DataSource.emotion1, {})
}

@Composable
fun EmotionsTable(
    emotions: List<Emotion>,
    onEmotionPressed: (Emotion) -> Unit
) {
    LazyColumn(Modifier.fillMaxWidth()) {
        item {
            Text(
                text = "Emotions",
                modifier = Modifier
                    .background(Color.LightGray)
                    .border(1.dp, Color.Black)
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }

        items(emotions) {
            Box(Modifier.clickable {
                onEmotionPressed(it)
            }) {
                Text(
                    text = it.content.joinToString(", "),
                    modifier = Modifier
                        .border(1.dp, Color.Black)
                        .padding(8.dp)
                        .fillMaxWidth(),
                )
            }
        }

        item {
            Text(
                text = "Add new emotion",
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun EmotionsTablePreview() {
    EmotionsTable(DataSource.journal1.emotions, {})
}