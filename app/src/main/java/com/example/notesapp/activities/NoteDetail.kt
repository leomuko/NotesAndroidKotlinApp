package com.example.notesapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityNoteDetailBinding
import com.example.notesapp.helpers.sampleData
import com.example.notesapp.models.NotesData
import kotlinx.android.synthetic.main.activity_notes_list.*

class NoteDetail : AppCompatActivity() {

    private lateinit var binding: ActivityNoteDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_detail)

        var myData : NotesData? = intent.getParcelableExtra("notes")
        binding.theNote.text = myData?.title
        binding.noteDetail.text = myData?.noteContent

        //onclick edit icon
        binding.editNoteIcon.setOnClickListener {
            Toast.makeText(this,"Clicked Edit Icon",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, EditNote::class.java)
            intent.putExtra("noteToEdit",myData)
            startActivity(intent)

        }

        //onclick delete icon
        binding.deleteNoteIcon.setOnClickListener {
            var noteId = myData?.id
            sampleData.deleteNote(noteId)
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
