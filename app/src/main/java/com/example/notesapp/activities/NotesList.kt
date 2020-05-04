package com.example.notesapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityNotesListBinding

class NotesList : AppCompatActivity() {
    private lateinit var binding: ActivityNotesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)
    }
}
