package com.example.notesapp.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "notes_table")
data class NoteDatabaseModel(

    @PrimaryKey(autoGenerate = true)
    var noteId: Int? = null,
    @ColumnInfo(name = "note_title")
    var note_title: String ,
    @ColumnInfo(name = "note_details")
    var note_details: String
): Parcelable
{

}