package com.example.notesapp.database

import android.icu.text.CaseMap
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDatabaseDao {

    @Insert
    fun insert(note: NoteDatabaseModel)

//    @Update
//    fun update(note: NoteDatabaseModel)

    @Delete
    fun delete(note: NoteDatabaseModel)

    @Query("SELECT * FROM notes_table ORDER BY noteId ASC")
    fun getAllNotes(): LiveData<List<NoteDatabaseModel>>

    @Query("UPDATE notes_table SET note_title = :title, note_details = :details WHERE noteId = :id ")
    fun update (id :Int, title: String, details: String)

    @Query("DELETE FROM notes_table")
    fun deleteAllNotes()
}