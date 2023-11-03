package com.example.moodjournal.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.moodjournal.ui.home.HomeDestination
import com.example.moodjournal.ui.home.HomeScreen
import com.example.moodjournal.ui.journal.JournalEntryDestination
import com.example.moodjournal.ui.journal.JournalEntryScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoodJournalNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToJournalEntry = { navController.navigate(JournalEntryDestination.route) },
                navigateToJournalUpdate = {},
            )
        }

        composable(route = JournalEntryDestination.route) {
            JournalEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}