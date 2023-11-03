package com.example.moodjournal.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.moodjournal.MoodJournalApplication
import com.example.moodjournal.ui.home.HomeViewModel

object AppViewModelProvider {
    @RequiresApi(Build.VERSION_CODES.O)
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(moodJournalApplication().container.journalRepository)
        }
    }
}

fun CreationExtras.moodJournalApplication(): MoodJournalApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as MoodJournalApplication)