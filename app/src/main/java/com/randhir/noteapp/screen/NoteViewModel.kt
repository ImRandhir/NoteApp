package com.randhir.noteapp.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.randhir.noteapp.data.NotesDataSource
import com.randhir.noteapp.model.Note

// Just a normal class with more power - This class is now responsible for our data and it's state.
class NoteViewModel : ViewModel() {

    private var noteList = mutableStateListOf<Note>()

    // Initializing ViewModel with data
    init {
        noteList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(note: Note){
        noteList.add(note)
    }

    fun removeNote(note: Note){
        noteList.remove(note)
    }

    fun getAllNotes() : List<Note>{
        return noteList
    }

}