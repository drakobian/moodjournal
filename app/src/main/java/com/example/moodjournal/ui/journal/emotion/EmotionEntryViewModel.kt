package com.example.moodjournal.ui.journal.emotion

import android.health.connect.datatypes.units.Percentage
import com.example.moodjournal.data.emotion.Emotion

class EmotionEntryViewModel {
}

data class EmotionDetails(
    val id: Int = 0,
    val content: String = "",
    val nowPercent: Int = 0,
    val goalPercent: Int = 0,
    val afterPercent: Int = 0,
)

fun Emotion.toEmotionDetails(): EmotionDetails = EmotionDetails(
    id = id,
    content = content,
    nowPercent = nowPercent,
    goalPercent = goalPercent,
    afterPercent = afterPercent,
)