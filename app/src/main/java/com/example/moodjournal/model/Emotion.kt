package com.example.moodjournal.model

data class Emotion(
    val content: List<String>,
    val nowPercent: Int,
    val goalPercent: Int,
    val afterPercent: Int,
)
