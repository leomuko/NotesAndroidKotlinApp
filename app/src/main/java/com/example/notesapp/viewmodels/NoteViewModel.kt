package com.example.notesapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesapp.database.NoteDatabaseModel
import com.example.notesapp.database.NoteRepository

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private var repository: NoteRepository = NoteRepository(application)

    private var allNotes: LiveData<List<NoteDatabaseModel>> = repository.getAllNotes()

    fun insert(note: NoteDatabaseModel){
        repository.insertNewNote(note)
    }

    fun update(id: Int, noteTitle: String, noteDetails: String){
        repository.updateNote(id,noteTitle,noteDetails)
    }

    fun delete(note: NoteDatabaseModel){
        repository.deleteNote(note)
    }
    fun getAllNotes(): LiveData<List<NoteDatabaseModel>>{
        return allNotes
    }
    fun deleteAllNotes(){
        repository.deleteAllNotes()
    }

}