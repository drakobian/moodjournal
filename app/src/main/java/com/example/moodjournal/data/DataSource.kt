package com.example.moodjournal.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.moodjournal.model.Distortion
import com.example.moodjournal.model.Emotion
import com.example.moodjournal.model.Journal
import com.example.moodjournal.model.Thought
import java.time.LocalDate

object DataSource {
    val thought1 = Thought(
        content = "negative thought one",
        nowPercent = 80,
        afterPercent = 5,
        distortions = listOf(Distortion.AON, Distortion.FT, Distortion.SH),
        positiveThought = "positive thought one :)",
        beliefPercent = 100
    )

    val thought2 = Thought(
        content = "negative thought two. This one is longer, and more negative, if you can believe! ugh. blah blah blah blah blah blah blah blah blah blah blah blah " +
                "blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah" +
                " blah blah blah blah blah blah blah blah blah blah blah blah blah blah blah ",
        nowPercent = 77,
        afterPercent = 0,
        distortions = listOf(Distortion.DP, Distortion.MF, Distortion.SH, Distortion.ER, Distortion.SB),
        positiveThought = "positive thought two :) positive thought two :) positive thought two :) positive thought two :) positive thought two :)" +
                "positive thought two :) positive thought two :) positive thought two :) positive thought two :) positive thought two :)" +
                "positive thought two :) positive thought two :) positive thought two :) positive thought two :) positive thought two :) positive thought two :)" +
                "positive thought two :) positive thought two :) positive thought two :) positive thought two :) positive thought two :) " +
                "positive thought two :) positive thought two :) positive thought two :)",
        beliefPercent = 88
    )

    val thoughts = listOf(thought1, thought2)

    val emotion1 = Emotion(
        content = listOf("sad", "blue", "depressed", "down", "unhappy"),
        nowPercent = 75,
        goalPercent = 5,
        afterPercent = 15
    )

    val emotion2 = Emotion(
        content = listOf("anxious", "worried", "panicky", "nervous", "frightened"),
        nowPercent = 99,
        goalPercent = 0,
        afterPercent = 0,
    )

    val emotions = listOf(emotion1, emotion2)

    @RequiresApi(Build.VERSION_CODES.O)
    val journal1 = Journal(
        event = "I did something and people responded in some way",
        date = LocalDate.now().minusDays(1),
        emotions = emotions,
        thoughts = thoughts,
    )

    @RequiresApi(Build.VERSION_CODES.O)
    val journal2 = Journal(
        event = "Another thing happened and I was like ughhghgh no",
        date = LocalDate.now(),
        emotions = emotions,
        thoughts = thoughts,
    )

    @RequiresApi(Build.VERSION_CODES.O)
    val journals = listOf(journal2, journal1)
}