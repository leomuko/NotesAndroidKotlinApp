package com.example.notesapp.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.R
import com.example.notesapp.database.NoteDatabaseModel
import com.example.notesapp.databinding.ActivityEditNoteBinding
import com.example.notesapp.helpers.sampleData
import com.example.notesapp.models.NotesData
import com.example.notesapp.viewmodels.NoteViewModel

class EditNote : AppCompatActivity() {

    private lateinit var binding: ActivityEditNoteBinding
    private lateinit var noteViewModel: NoteViewModel
    var noteToEdit: NoteDatabaseModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_note)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)


        noteToEdit  = intent.getParcelableExtra("notes")
        binding.noteContent.setText(noteToEdit?.note_details)
        binding.noteTitle.setText(noteToEdit?.note_title)


        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        //onBackPressed()
        if (noteToEdit != null) saveNote(noteToEdit!!)
        return true
    }
    private fun saveNote(note: NoteDatabaseModel){
        if(binding.noteTitle.text.toString().trim().isBlank() || binding.noteContent.text.toString().trim().isBlank()){
            Toast.makeText(this,"Can not insert empty note", Toast.LENGTH_SHORT).show()
            return
        }
        val newNote = NoteDatabaseModel(
            note.noteId,
        binding.noteTitle.text.toString(),
        binding.noteContent.text.toString()
        )
        val data = Intent(this, NotesList::class.java).apply {
           putExtra("title", newNote.note_title)
            putExtra("details", newNote.note_details)
            if (newNote.noteId != -1) {
                putExtra("noteId", newNote.noteId)
            }

        }


        setResult(Activity.RESULT_OK, data)
        finish()

    }

}
