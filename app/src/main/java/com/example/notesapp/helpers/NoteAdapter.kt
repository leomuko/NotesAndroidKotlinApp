package com.example.notesapp.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.database.NoteDatabaseModel
import kotlinx.android.synthetic.main.list.view.*

class NoteAdapter internal constructor
    ( private val listener: OnItemClickListener) : RecyclerView.Adapter<NoteAdapter.RecyclerViewHolder>() {

    private var recyclerList: List<NoteDatabaseModel> = emptyList()
    //private var clickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun getItemCount() = recyclerList.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        listener.let { holder.initialize(recyclerList[position], it) }
//        var currentNote: NoteDatabaseModel = recyclerList[position]
//        holder.noteTitle.text = currentNote.note_title
    }

    fun setNotes(notes: List<NoteDatabaseModel>) {
        this.recyclerList = notes
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int): NoteDatabaseModel{
        return recyclerList[position]
    }


    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var noteTitle: TextView = itemView.the_Note

        fun initialize(item: NoteDatabaseModel, action: OnItemClickListener) {
            noteTitle.text = item.note_title

            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }
        }


    }



}
interface OnItemClickListener {
    fun onItemClick(item: NoteDatabaseModel, position: Int)
}
