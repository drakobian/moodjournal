package com.example.moodjournal.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodjournal.MoodJournalTopAppBar
import com.example.moodjournal.data.DataSource
import com.example.moodjournal.data.Journal
import com.example.moodjournal.ui.AppViewModelProvider
import com.example.moodjournal.ui.navigation.NavigationDestination
import com.example.moodjournal.ui.theme.MoodJournalTheme
import java.time.format.DateTimeFormatter

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = "Mood Journal"
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToJournalEntry: () -> Unit,
    navigateToJournalUpdate: (Int) -> Unit,
    modifier : Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MoodJournalTopAppBar(
                title = "Mood Journals",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToJournalEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add new journal"
                )
            }
        }
    ) { innerPadding ->
        HomeBody(
            journalList = homeUiState.journalList,
            onJournalClick = navigateToJournalUpdate,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeBody(
    journalList: List<Journal>,
    onJournalClick: (Int) -> Unit,
    modifier : Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (journalList.isEmpty()) {
            Text(
                text = "No journals yet!",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            JournalList(
                journalList,
                onJournalClick,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun JournalList(
    journals: List<Journal>,
    onJournalPressed: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(Modifier.fillMaxWidth()) {
        items(journals) {
            Card(
                modifier = Modifier.padding(8.dp),
                onClick = { onJournalPressed(0) }
            ) {
                Text(
                    text = it.date.format(DateTimeFormatter.ofPattern("MMMM dd, uuuu")),
                    modifier = Modifier
                        //.border(1.dp, Color.Black)
                        .padding(16.dp)
                        .fillMaxWidth(),
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomeBodyPreview() {
    MoodJournalTheme {
        HomeBody(listOf(), onJournalClick = {})
    }
}