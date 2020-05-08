package com.example.notesapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesapp.database.DatabaseModel
import com.example.notesapp.database.NoteRepository

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private var repository: NoteRepository = NoteRepository(application)

    private var allNotes: LiveData<List<DatabaseModel>> = repository.getAllNotes()

    fun insert(note: DatabaseModel){
        repository.insertNewNote(note)
    }

    fun update(note: DatabaseModel){
        repository.updateNote(note)
    }

    fun delete(note: DatabaseModel){
        repository.deleteNote(note)
    }
    fun getAllNotes(): LiveData<List<DatabaseModel>>{
        return allNotes
    }

}