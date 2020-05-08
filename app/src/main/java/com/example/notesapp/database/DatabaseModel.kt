package com.example.notesapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes_table")
data class DatabaseModel(

    @ColumnInfo(name = "note_title")
    var note_title: String? = null,
    @ColumnInfo(name = "note_details")
    var note_details: String? = null
){
    @PrimaryKey(autoGenerate = true)
    var noteId: Int = 0
}