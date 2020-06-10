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
import com.example.notesapp.R
import com.example.notesapp.database.NoteDatabaseModel
import com.example.notesapp.databinding.ActivityCreateNoteBinding
import com.example.notesapp.helpers.sampleData
import com.example.notesapp.models.NotesData
import com.example.notesapp.viewmodels.NoteViewModel
import petrov.kristiyan.colorpicker.ColorPicker

class CreateNote : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNoteBinding

    var noteColor = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_note)


        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        //onBackPressed()
        saveNote()
        onBackPressed()
        return true
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_or_create_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.delete_note -> {
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
                    noteColor = color
                    Log.d("Color", "${noteColor}")
                    binding.createNoteMainLayout.setBackgroundColor(noteColor)
                }

                override fun onCancel() {

                }

            }).show()
    } */

    private fun saveNote() {
        if (binding.noteTitle.text.toString().trim()
                .isBlank() || binding.noteContent.text.toString().trim().isBlank()
        ) {
           // Toast.makeText(this, "Can not insert empty note", Toast.LENGTH_SHORT).show()
            return
        }
        val data = Intent().apply {
            putExtra("title", binding.noteTitle.text.toString())
            putExtra("details", binding.noteContent.text.toString())
            putExtra("noteColor",noteColor)
        }
        Log.d("Create","$noteColor")
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}
