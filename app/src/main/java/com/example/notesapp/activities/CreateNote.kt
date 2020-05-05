package com.example.notesapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityCreateNoteBinding

class CreateNote : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_note)


    }
}
