package com.randhir.noteapp.model

import android.content.ClipDescription
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

// Entity from ROOM will create a database table from our data class
@Entity(tableName = "notes_tbl")
data class Note(
    @PrimaryKey // This tells Room , this is the unique key all elements of an object will be accessed with in database tbl.
    val id: UUID = UUID.randomUUID(), // It will create a random unique id for each note

    @ColumnInfo(name = "note_title")  // Name of title column in database.
    val title: String,

    @ColumnInfo(name = "note_description")
    val description: String,

    @ColumnInfo(name = "note_entry_date")
    val entryDate: Date = Date.from(Instant.now())
)
