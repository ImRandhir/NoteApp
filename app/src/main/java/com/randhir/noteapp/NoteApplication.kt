package com.randhir.noteapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//This gives Hilt access to entire application , that's why we extended to application
@HiltAndroidApp
class NoteApplication : Application() {

}