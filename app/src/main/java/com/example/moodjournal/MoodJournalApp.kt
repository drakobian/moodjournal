package com.example.moodjournal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.moodjournal.ui.navigation.MoodJournalNavHost
import com.example.moodjournal.ui.theme.MoodJournalTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoodJournalApp(navController: NavHostController = rememberNavController()) {
    MoodJournalNavHost(navController = navController)
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun MoodJournalAppPreview() {
    MoodJournalTheme {
        MoodJournalApp()
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MoodJournalTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Go Back"
                    )
                }
            }
        }
    )
}