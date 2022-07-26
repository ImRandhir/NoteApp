package com.randhir.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.randhir.noteapp.model.Note
import com.randhir.noteapp.util.DateConverter
import com.randhir.noteapp.util.UUIDConverter

@Database(entities = [Note::class] , version = 1 , exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase: RoomDatabase() {


    abstract fun NoteDao(): NoteDatabaseDao

}