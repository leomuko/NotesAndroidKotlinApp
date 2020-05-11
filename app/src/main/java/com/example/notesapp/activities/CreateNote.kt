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
import com.example.notesapp.databinding.ActivityCreateNoteBinding
import com.example.notesapp.helpers.sampleData
import com.example.notesapp.models.NotesData
import com.example.notesapp.viewmodels.NoteViewModel

class CreateNote : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNoteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_note)




        binding.saveButton.setOnClickListener {
            saveNote()
        }




        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun saveNote(){
        if(binding.noteTitle.text.toString().trim().isBlank() || binding.noteContent.text.toString().trim().isBlank()){
            Toast.makeText(this,"Can not insert empty note", Toast.LENGTH_SHORT).show()
            return
        }
        val data = Intent().apply {
            putExtra("title", binding.noteTitle.text.toString())
            putExtra("details", binding.noteContent.text.toString())
        }
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}
