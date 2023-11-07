package com.example.moodjournal.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.moodjournal.MoodJournalApplication
import com.example.moodjournal.ui.home.HomeViewModel
import com.example.moodjournal.ui.journal.JournalDetailsViewModel
import com.example.moodjournal.ui.journal.JournalEditViewModel
import com.example.moodjournal.ui.journal.JournalEntryViewModel
import com.example.moodjournal.ui.journal.emotion.EmotionEditViewModel
import com.example.moodjournal.ui.journal.emotion.EmotionEntryViewModel
import com.example.moodjournal.ui.journal.thought.ThoughtEntryViewModel

object AppViewModelProvider {
    @RequiresApi(Build.VERSION_CODES.O)
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(moodJournalApplication().container.journalRepository)
        }

        initializer {
            JournalEntryViewModel(moodJournalApplication().container.journalRepository)
        }

        initializer {
            JournalDetailsViewModel(
                this.createSavedStateHandle(),
                moodJournalApplication().container.journalRepository,
                moodJournalApplication().container.emotionRepository,
                moodJournalApplication().container.thoughtRepository,
            )
        }

        initializer {
            JournalEditViewModel(
                this.createSavedStateHandle(),
                moodJournalApplication().container.journalRepository
            )
        }

        initializer {
            EmotionEditViewModel(
                this.createSavedStateHandle(),
                moodJournalApplication().container.emotionRepository,
            )
        }

        initializer {
            EmotionEntryViewModel(
                this.createSavedStateHandle(),
                moodJournalApplication().container.emotionRepository,
            )
        }

        initializer {
            ThoughtEntryViewModel(
                this.createSavedStateHandle(),
                moodJournalApplication().container.thoughtRepository,
            )
        }
    }
}

fun CreationExtras.moodJournalApplication(): MoodJournalApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as MoodJournalApplication)