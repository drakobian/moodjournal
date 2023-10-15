package com.example.moodjournal.ui

import androidx.activity.compose.BackHandler
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
import com.example.moodjournal.model.Thought

@Composable
fun ThoughtScreen(thought: Thought, onBackPressed: () -> Unit) {
    BackHandler {
        // hmm is this an idea?
        onBackPressed()
    }

    Column {
        Text(
            text = "Negative thought: ${thought.content}"
        )

        Row {
            Text(text = "Now: ${thought.nowPercent}%")
            Text(text = "   After: ${thought.afterPercent}%")
        }

        Row {
            Text(text = "Distortions: ${thought.distortions.joinToString(", ")}")
        }

        Text(text = "Positive thought: ${thought.positiveThought}")

        Text(text = "Belief: ${thought.beliefPercent}%")
    }
}

@Preview
@Composable
fun ThoughtScreenPreview() {
    ThoughtScreen(DataSource.thought1, {})
}

@Composable
fun ThoughtsTable(
    thoughts: List<Thought>,
    onThoughtPressed: (Thought) -> Unit
) {
    LazyColumn(Modifier.fillMaxWidth()) {
        item {
            Text(
                text = "Negative thoughts",
                modifier = Modifier
                    .background(Color.LightGray)
                    .border(1.dp, Color.Black)
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }

        items(DataSource.thoughts) {
            Box(Modifier.clickable {
                onThoughtPressed(it)
            }) {
                Text(
                    text = it.content,
                    modifier = Modifier
                        .border(1.dp, Color.Black)
                        .padding(8.dp)
                        .fillMaxWidth(),
                )
            }
        }

        item {
            Text(
                text = "Add new thought",
                modifier = Modifier
                    .border(1.dp, Color.Black)
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun ThoughtsTablePreview() {
    ThoughtsTable(DataSource.journal1.thoughts, {})
}