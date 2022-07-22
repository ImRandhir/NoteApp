package com.randhir.noteapp.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.randhir.noteapp.R
import com.randhir.noteapp.components.NoteButton
import com.randhir.noteapp.components.NoteInputText
import com.randhir.noteapp.data.NotesDataSource
import com.randhir.noteapp.model.Note
import java.time.format.DateTimeFormatter


// We will put this note screen in NoteApp theme in main activity , less code in Main Activity :)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteScreen(notes: List<Note> , onAddNote: (Note) -> Unit , onRemoveNote: (Note) -> Unit){

    // 2 states - for Title and Note
    var title by remember{
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    // We have to use this context in Toast
    val context = LocalContext.current

    // For hiding keyboard on click Save
    val keyboardController = LocalSoftwareKeyboardController.current
        
    Column(modifier = Modifier) {

        //Our TopBar
        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name) , fontWeight = FontWeight.Bold)}
            , actions = { Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Icon")}
            , backgroundColor = Color(0xFFC6CCD1))

        Column(modifier = Modifier.fillMaxWidth(),
               horizontalAlignment = Alignment.CenterHorizontally) {
                    
                NoteInputText(text = title, label = "Title", onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                }, modifier = Modifier.padding(top = 10.dp , bottom = 8.dp))

                NoteInputText(text = description, label = "Add a note", onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                }, modifier = Modifier.padding(top = 10.dp , bottom = 8.dp))

                NoteButton(  text = "Save",
                            onClick = {
                               if(title.isNotEmpty() && description.isNotEmpty()){
                                 onAddNote(Note(title = title, description = description))

                                 title = ""
                                 description = ""

                                 keyboardController?.hide()

                                 Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                               }
                            })

        }

        Divider(modifier = Modifier.padding(10.dp))
        
        LazyColumn{
            items(notes){ note ->
                NoteRow(note = note,
                    onNoteClicked = {onRemoveNote(note)
                        Toast.makeText(context, "Note Removed", Toast.LENGTH_SHORT).show()})
            }
        }

    }

}

@Composable
fun NoteRow(modifier: Modifier = Modifier,
            note: Note,
            onNoteClicked: (Note) -> Unit){

    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 20.dp))
            .fillMaxWidth(), color = Color(0xFFDFE6EB), elevation = 6.dp) {

        Column(
            modifier
                .padding(horizontal = 25.dp, vertical = 6.dp)
                .clickable {onNoteClicked(note) } ,
                horizontalAlignment = Alignment.Start) {
                
            Text(text = note.title,
                style = MaterialTheme.typography.subtitle2)

            Text(text = note.description,
                 style = MaterialTheme.typography.subtitle1)

            Text(text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
                style = MaterialTheme.typography.caption)

        }
    }
}





@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen(notes = NotesDataSource().loadNotes()  , onAddNote = {}, onRemoveNote = {})
}