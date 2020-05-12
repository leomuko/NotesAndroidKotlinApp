package com.example.notesapp.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.database.NoteDatabaseModel
import com.example.notesapp.databinding.ActivityNotesListBinding
import com.example.notesapp.helpers.OnItemClickListener
import com.example.notesapp.helpers.NoteAdapter
import com.example.notesapp.viewmodels.NoteViewModel

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class NotesList : AppCompatActivity(), OnItemClickListener {

    companion object {
        val ADD_NOTE_REQUEST = 1
        val EDIT_NOTE_REQUEST = 2

    }

    private lateinit var binding: ActivityNotesListBinding

    private lateinit var noteViewModel: NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notes_list)

        var adapter = NoteAdapter(this)
        binding.recyclerView.adapter = adapter

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)


        noteViewModel.getAllNotes().observe(this, Observer { notes ->
            notes?.let { adapter.setNotes(it) }

        })

        binding.addNoteButton.setOnClickListener {

            startActivityForResult(
                Intent(this, CreateNote::class.java),
                ADD_NOTE_REQUEST
            )
        }



        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
               return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.adapterPosition))
                Toast.makeText(baseContext, "Note Deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(binding.recyclerView)

    }


    override fun onItemClick(item: NoteDatabaseModel, position: Int) {
        val intent = Intent(this, EditNote::class.java)
        intent.putExtra("notes", item)

        startActivityForResult(intent, EDIT_NOTE_REQUEST)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId){
            R.id.delete_all_notes -> {
                noteViewModel.deleteAllNotes()
                Toast.makeText(this, "All notes deleted!", Toast.LENGTH_SHORT).show()
                true
            }
            else ->{
                super.onOptionsItemSelected(item)
            }
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            val newNote = NoteDatabaseModel(
                null,
                data!!.getStringExtra("title"),
                data.getStringExtra("details")
            )

            noteViewModel.insert(newNote)
            Toast.makeText(this, "Note has been added", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            val updateNote = NoteDatabaseModel(
                data?.getIntExtra("noteId", -1),
                data!!.getStringExtra("title"),
                data.getStringExtra("details")
            )

            updateNote.noteId?.let {
                noteViewModel.update(
                    it,
                    updateNote.note_title,
                    updateNote.note_details
                )
            }
            Toast.makeText(this, "Note Edited", Toast.LENGTH_SHORT).show()
            Log.i("Edit", updateNote.noteId.toString())
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show()
        }
    }
}
