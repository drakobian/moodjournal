package com.example.moodjournal.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.moodjournal.ui.home.HomeDestination
import com.example.moodjournal.ui.home.HomeScreen
import com.example.moodjournal.ui.journal.JournalDetailsDestination
import com.example.moodjournal.ui.journal.JournalDetailsScreen
import com.example.moodjournal.ui.journal.JournalEditDestination
import com.example.moodjournal.ui.journal.JournalEditScreen
import com.example.moodjournal.ui.journal.JournalEntryDestination
import com.example.moodjournal.ui.journal.JournalEntryScreen
import com.example.moodjournal.ui.journal.emotion.EmotionEntryDestination
import com.example.moodjournal.ui.journal.emotion.EmotionEntryScreen
import com.example.moodjournal.ui.journal.thought.ThoughtEntryDestination
import com.example.moodjournal.ui.journal.thought.ThoughtEntryScreen

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
                navigateToJournalUpdate = { navController.navigate("${JournalDetailsDestination.route}/${it}") },
            )
        }

        composable(route = JournalEntryDestination.route) {
            JournalEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }

        composable(
            route = JournalEditDestination.routeWithArgs,
            arguments = listOf(navArgument(JournalEditDestination.journalIdArg) {
                type = NavType.IntType
            })
        ) {
            JournalEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }

        composable(
            route = JournalDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(JournalDetailsDestination.journalIdArg) {
                type = NavType.IntType
            })
        ) {
            JournalDetailsScreen(
                navigateToEditJournal = { navController.navigate("${JournalEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() },
                navigateToEmotionEntry = { journalId ->
                    {
                        navController.navigate("${EmotionEntryDestination.route}/${journalId}")
                    }
                },
                navigateToThoughtEntry = { journalId ->
                    {
                        navController.navigate("${ThoughtEntryDestination.route}/${journalId}")
                    }
                }
            )
        }

        composable(
            route = EmotionEntryDestination.routeWithArgs,
            arguments = listOf(navArgument(EmotionEntryDestination.journalIdArg) {
                type = NavType.IntType
            })
        ) {
            EmotionEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }

        composable(
            route = ThoughtEntryDestination.routeWithArgs,
            arguments = listOf(navArgument(ThoughtEntryDestination.journalIdArg) {
                type = NavType.IntType
            })
        ) {
            ThoughtEntryScreen( navigateBack = { navController.popBackStack() })
        }
    }
}