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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moodjournal.data.DataSource
import com.example.moodjournal.model.Emotion

@OptIn(ExperimentalMaterial3Api::class)
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
            TextField(
                value = "${emotion.nowPercent}",
                onValueChange = {},
                label = { Text("Now Percentage") },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            TextField(
                value = "${emotion.goalPercent}",
                onValueChange = {},
                label = { Text("Goal Percentage") },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            TextField(
                value = "${emotion.afterPercent}",
                onValueChange = {},
                label = { Text("After Percentage") },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
        }
    }
}

@Preview
@Composable
fun EmotionScreenPreview() {
    EmotionScreen(DataSource.emotion1, {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmotionsTable(
    emotions: List<Emotion>,
    onEmotionPressed: (Emotion) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        item {
            Text(
                text = "Emotions",
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
            Divider()
        }

        items(emotions) {
            ListItem(
                modifier = Modifier
                    .clickable{ onEmotionPressed(it) },
                headlineText = {
                    Text(it.content.joinToString(", "))
                },
                trailingContent = {
                    Icon(
                        Icons.Filled.KeyboardArrowRight,
                        contentDescription = null
                    )
                },
                colors = ListItemDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }

        item {
            Divider()
            ListItem(
                headlineText = {
                    Text("Add new emotion")
                },
                trailingContent = {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = null
                    )
                },
                colors = ListItemDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
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