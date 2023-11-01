package com.example.moodjournal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.moodjournal.ui.navigation.MoodJournalNavHost

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoodJournalApp(navController: NavHostController = rememberNavController()) {
    MoodJournalNavHost(navController = navController)
}