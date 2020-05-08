package com.example.notesapp.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class NoteRepository(application: Application)  {

    private var viewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var noteDao: NoteDatabaseDao

    private var allNotes: LiveData<List<DatabaseModel>>
    init {
        val database: NoteDatabase = NoteDatabase.getInstance(
            application.applicationContext
        )!!
        noteDao = database.noteDao
        allNotes = noteDao.getAllNotes()
    }
    fun insertNewNote(note: DatabaseModel){
        uiScope.launch {
            val newNote = DatabaseModel()
            insert(newNote)
        }
    }
    private suspend fun insert(note: DatabaseModel){
        withContext(Dispatchers.IO){
            noteDao.insert(note)
        }

    }

    fun updateNote(note: DatabaseModel){
        uiScope.launch {
            upDate(note)
        }

    }
    private suspend fun upDate(note: DatabaseModel){
        withContext(Dispatchers.IO){
            noteDao.update(note)
        }
    }

    fun deleteNote(note: DatabaseModel){
        uiScope.launch {
            delete(note)
        }
    }

    private suspend fun delete(note: DatabaseModel){
        withContext(Dispatchers.IO){
            noteDao.delete(note)
        }
    }
    fun getAllNotes(): LiveData<List<DatabaseModel>>{
        return allNotes
    }


}