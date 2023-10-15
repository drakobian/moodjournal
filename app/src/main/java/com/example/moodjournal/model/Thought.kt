package com.example.moodjournal.model

data class Thought(
    val content: String,
    val nowPercent: Int,
    val afterPercent: Int,
    val distortions: List<Distortion>,
    val positiveThought: String,
    val beliefPercent: Int,
)
