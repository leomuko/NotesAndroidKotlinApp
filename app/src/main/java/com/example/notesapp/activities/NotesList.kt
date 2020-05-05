package com.example.notesapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.R
import com.example.notesapp.databinding.ActivityNotesListBinding
import com.example.notesapp.helpers.onItemClickListener
import com.example.notesapp.helpers.sampleData
import com.example.notesapp.helpers.theAdapter
import com.example.notesapp.models.NotesData

class NotesList : AppCompatActivity(), onItemClickListener {
    private lateinit var binding: ActivityNotesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_notes_list)

        binding.addNoteButton.setOnClickListener {
            val intent = Intent(this, CreateNote::class.java)
            startActivity(intent)

        }
        binding.recyclerView.adapter = theAdapter(sampleData.NOTES,this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    }


    override fun onItemClick(item: NotesData, position: Int) {
        val intent = Intent(this, NoteDetail::class.java)
        val data = sampleData.NOTES.get(position)
        intent.putExtra("notes", data )
        startActivity(intent)
    }
}
