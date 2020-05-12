package com.example.notesapp.database

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class NoteRepository(application: Application)  {

    private var viewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var noteDao: NoteDatabaseDao

    private var allNotes: LiveData<List<NoteDatabaseModel>>
    init {
        val database: NoteDatabase = NoteDatabase.getInstance(
            application.applicationContext
        )!!
        noteDao = database.noteDao
        allNotes = noteDao.getAllNotes()
    }
    fun insertNewNote(note: NoteDatabaseModel){
        uiScope.launch {

            insert(note)
        }
    }
    private suspend fun insert(note: NoteDatabaseModel){
        withContext(Dispatchers.IO){
            noteDao.insert(note)
        }

    }

    fun updateNote(id: Int, title: String, details: String){
        uiScope.launch {
            upDate(id, title, details)
        }

    }
    private suspend fun upDate(id: Int, title: String, details: String){
        withContext(Dispatchers.IO){
            noteDao.update(id,title,details)
        }
    }

    fun deleteNote(note: NoteDatabaseModel){
        uiScope.launch {
            delete(note)
        }
    }

    private suspend fun delete(note: NoteDatabaseModel){
        withContext(Dispatchers.IO){
            noteDao.delete(note)
        }
    }
    fun getAllNotes(): LiveData<List<NoteDatabaseModel>>{
        return allNotes
    }
    fun deleteAllNotes(){
        uiScope.launch {
            deleteAll()
        }
    }
    private suspend fun deleteAll(){
        withContext(Dispatchers.IO){
            noteDao.deleteAllNotes()
        }
    }


}