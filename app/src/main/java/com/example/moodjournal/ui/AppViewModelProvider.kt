package com.example.moodjournal.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.moodjournal.ui.home.HomeViewModel

object AppViewModelProvider {
    @RequiresApi(Build.VERSION_CODES.O)
    val Factory = viewModelFactory {
        initializer {
            // todo: init w/ repo
            HomeViewModel()
        }
    }
}

/* do I need this? */
/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
//fun CreationExtras.inventoryApplication(): InventoryApplication =
//    (this[AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)