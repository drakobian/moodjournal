package com.example.moodjournal

import android.app.Application
import com.example.moodjournal.data.AppContainer
import com.example.moodjournal.data.AppDataContainer

class MoodJournalApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}