package com.example.notesapp.viewmodels

import android.app.Application
import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.style.BulletSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.widget.EditText
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesapp.R
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
   /* fun setBoldText(editText: EditText): SpannableStringBuilder {
        val boldStyle = StyleSpan(Typeface.BOLD)
        val txtToEdit = editText.text.toString()
        val start = editText.selectionStart
        val end = editText.selectionEnd
        val sb = SpannableStringBuilder(txtToEdit)
        sb.setSpan(boldStyle,start,end,0)
        return sb

    }
    fun setItalicText(editText: EditText): SpannableStringBuilder{
        val italicStyle = StyleSpan(Typeface.ITALIC)
        val txtToEdit = editText.text.toString()
        val start = editText.selectionStart
        val end = editText.selectionEnd
        val sb = SpannableStringBuilder(txtToEdit)
        sb.setSpan(italicStyle,start,end,0)
        return sb
    }
    fun underlineText(editText: EditText): SpannableStringBuilder{
        val underlineStyle = UnderlineSpan()
        val txtToEdit = editText.text.toString()
        val start = editText.selectionStart
        val end = editText.selectionEnd
        val sb = SpannableStringBuilder(txtToEdit)
        sb.setSpan(underlineStyle,start,end,0)
        return sb
    }
    fun strikeThroughText(editText: EditText): SpannableStringBuilder{
        val strikeStyle = StrikethroughSpan()
        val txtToEdit = editText.text.toString()
        val start = editText.selectionStart
        val end = editText.selectionEnd
        val sb = SpannableStringBuilder(txtToEdit)
        sb.setSpan(strikeStyle,start,end,0)
        return sb
    }
    fun bulletedText(editText: EditText): SpannableStringBuilder{

        val bulletStyle = BulletSpan(20,Color.BLACK,10)
        val txtToEdit = editText.text.toString()
        val start = editText.selectionStart
        val end = editText.selectionEnd
        val sb = SpannableStringBuilder(txtToEdit)
        sb.setSpan(bulletStyle,start,end,0)
        return sb
    } */

}