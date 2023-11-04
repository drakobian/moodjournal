package com.example.moodjournal.ui.journal

import androidx.compose.runtime.Composable
import com.example.moodjournal.ui.navigation.NavigationDestination

object JournalDetailsDestination : NavigationDestination {
    override val route = "journal_details"
    override val titleRes = "journal details"
    const val journalIdArg = "journalId"
    val routeWithArgs = "$route/{$journalIdArg}"
}

@Composable
fun JournalDetailsScreen() {

}