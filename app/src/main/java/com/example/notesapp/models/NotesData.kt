package com.example.notesapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NotesData (
    var id: Int = 0,
    var title: String? = null,
    var noteContent: String? = null
): Parcelable