package com.example.notesapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityNoteDetailBinding
import com.example.notesapp.models.NotesData

class NoteDetail : AppCompatActivity() {

    private lateinit var binding: ActivityNoteDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_detail)

        var myData : NotesData = intent.getParcelableExtra("notes")
        binding.theNote.text = myData.title
        binding.noteDetail.text = myData.noteContent

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
