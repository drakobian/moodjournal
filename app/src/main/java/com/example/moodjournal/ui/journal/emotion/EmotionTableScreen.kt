package com.example.moodjournal.ui.journal.emotion

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmotionsTable(
    emotions: List<EmotionDetails>,
    onEmotionPressed: (EmotionDetails) -> Unit,
    navigateToEmotionEntry: () -> Unit,
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
                modifier = Modifier
                    .clickable{ navigateToEmotionEntry() },
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