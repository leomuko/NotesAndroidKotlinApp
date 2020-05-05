package com.example.notesapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityEditNoteBinding
import com.example.notesapp.helpers.sampleData
import com.example.notesapp.models.NotesData

class EditNote : AppCompatActivity() {

    private lateinit var binding: ActivityEditNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_note)

        var noteToEdit: NotesData? = intent.getParcelableExtra("noteToEdit")
        binding.noteContent.setText(noteToEdit?.noteContent)
        binding.noteTitle.setText(noteToEdit?.title)

        binding.saveButton.setOnClickListener {
            var newNote = NotesData()
            if (noteToEdit != null) {
                newNote.id = noteToEdit.id
            }
            newNote.noteContent = binding.noteContent.text.toString()
            newNote.title = binding.noteTitle.text.toString()

            sampleData.editNote(newNote)

            Toast.makeText(this, "The note has been updated", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, NotesList::class.java)
            startActivity(intent)
        }

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
