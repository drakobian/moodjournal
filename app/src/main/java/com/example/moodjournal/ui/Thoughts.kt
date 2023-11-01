package com.example.moodjournal.ui

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moodjournal.data.DataSource
import com.example.moodjournal.model.Distortion
import com.example.moodjournal.model.Thought

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThoughtScreen(thought: Thought, onBackPressed: () -> Unit) {
    BackHandler {
        // hmm is this an idea?
        onBackPressed()
    }

    val scrollState = rememberScrollState()

    Column(/*Modifier.verticalScroll(scrollState)*/) {
        TextField(
            value = "${thought.content}",
            onValueChange = {},
            label = { Text("Negative thought") },
            modifier = Modifier.padding(4.dp).fillMaxWidth()
        )

        Row {
            TextField(
                value = "${thought.nowPercent}",
                onValueChange = {},
                label = { Text("Now Percentage") },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            TextField(
                value = "${thought.afterPercent}",
                onValueChange = {},
                label = { Text("After Percentage") },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
        }

        //Column(Modifier.padding(8.dp)) {
        Text("Distortions", Modifier.padding(8.dp))
        Box(Modifier.padding(8.dp)) {
            LazyVerticalGrid(columns = GridCells.Fixed(4)) {
                items(Distortion.values()) {
                    Row {
                        Checkbox(checked = thought.distortions.contains(it), onCheckedChange = null)
                        Text(it.name, Modifier.padding(start = 4.dp, top = 2.dp))
                    }
                }
            }
        }
            //Distortion.values().map {

//                ListItem(
//                    headlineText = {
//                        Text(it.name)
//                    },
//                    leadingContent = {
//                        Checkbox(checked = thought.distortions.contains(it), onCheckedChange = null)
//                    },
//                )
            //}
        //}

        TextField(
            value = "${thought.positiveThought}",
            onValueChange = {},
            label = { Text("Positive thought") },
            modifier = Modifier.padding(4.dp).fillMaxWidth()
        )

        TextField(
            value = "${thought.beliefPercent}",
            onValueChange = {},
            label = { Text("Belief Percentage") },
            modifier = Modifier.padding(4.dp).align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
fun ThoughtScreenPreview() {
    ThoughtScreen(DataSource.thought1, {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThoughtsTable(
    thoughts: List<Thought>,
    onThoughtPressed: (Thought) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        item {
            Text(
                text = "Negative thoughts",
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
            Divider()
        }

        items(thoughts) {
            ListItem(
                modifier = Modifier
                    .clickable{ onThoughtPressed(it) },
                headlineText = {
                    Text(it.content)
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
                    Text("Add new thought")
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
fun ThoughtsTablePreview() {
    ThoughtsTable(DataSource.journal1.thoughts, {})
}