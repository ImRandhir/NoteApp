package com.randhir.noteapp.model

import android.content.ClipDescription
import java.time.LocalDateTime
import java.util.*

data class Note(
    val id: UUID = UUID.randomUUID(), // It will create a random unique id for each note
    val title: String,
    val description: String,
    val entryDate: LocalDateTime = LocalDateTime.now()
)
