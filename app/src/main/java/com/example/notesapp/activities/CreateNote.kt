package com.example.notesapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityCreateNoteBinding
import com.example.notesapp.helpers.sampleData
import com.example.notesapp.models.NotesData

class CreateNote : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_note)



        binding.saveButton.setOnClickListener {
            val newNote = NotesData()
            newNote.title = binding.noteTitle.text.toString()
            newNote.noteContent = binding.noteContent.text.toString()
            sampleData.addNote(newNote)
            finish()
        }

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
