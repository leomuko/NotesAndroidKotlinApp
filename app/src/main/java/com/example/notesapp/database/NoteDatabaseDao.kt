package com.example.notesapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDatabaseDao {

    @Insert
    fun insert(note: DatabaseModel)

    @Update
    fun update(note: DatabaseModel)

    @Delete
    fun delete(note: DatabaseModel)

    @Query("SELECT * FROM notes_table ORDER BY noteId ASC")
    fun getAllNotes(): LiveData<List<DatabaseModel>>


}