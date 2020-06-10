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
import androidx.lifecycle.ViewModelProvider
import com.androidessence.lib.RichTextView
import com.example.notesapp.R
import com.example.notesapp.database.NoteDatabaseModel
import com.example.notesapp.databinding.ActivityEditNoteBinding

import com.example.notesapp.viewmodels.NoteViewModel
import kotlinx.android.synthetic.main.activity_edit_note.*
import petrov.kristiyan.colorpicker.ColorPicker


class EditNote : AppCompatActivity() {

    private lateinit var binding: ActivityEditNoteBinding
    private lateinit var noteViewModel: NoteViewModel
    var noteColor = 0
    var noteToEdit: NoteDatabaseModel? = null
    var edittedNoteContent = ""
    var edittedNoteTitle = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_note)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)


        noteToEdit = intent.getParcelableExtra("notes")
        binding.noteContent.setText(noteToEdit?.note_details)
        binding.noteTitle.setText(noteToEdit?.note_title)

        edittedNoteContent = binding.noteContent.text.toString()
        edittedNoteTitle = binding.noteTitle.text.toString()

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_or_create_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.delete_note -> {
                noteViewModel.delete(noteToEdit!!)
                true
            }
            R.id.go_to_notes_list -> {
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    }

  /*  private fun chooseColor() {
        val colorPicker: ColorPicker = ColorPicker(this)
        var colors = arrayListOf<String>()
        colors.add("#422523")
        colors.add("#a6f87e")
        colors.add("#534c5c")
        colors.add("#6e3abd")


        colorPicker.setColors(colors)
            .setColumns(4)
            .setRoundColorButton(true)
            .setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
                override fun onChooseColor(position: Int, color: Int) {

                }

                override fun onCancel() {

                }

            }).show()
    } */

    override fun onSupportNavigateUp(): Boolean {
        //onBackPressed()
        if (noteToEdit != null) saveNote(noteToEdit!!)
        return true
    }

    private fun saveNote(note: NoteDatabaseModel) {
        if (binding.noteTitle.text.toString().trim()
                .isBlank() || binding.noteContent.text.toString().trim().isBlank()
        ) {
           // Toast.makeText(this, "Can not insert empty note", Toast.LENGTH_SHORT).show()
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
