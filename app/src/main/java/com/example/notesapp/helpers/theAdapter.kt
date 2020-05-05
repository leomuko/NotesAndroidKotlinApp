package com.example.notesapp.helpers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.activities.NotesList
import com.example.notesapp.models.NotesData
import kotlinx.android.synthetic.main.activity_create_note.view.*
import kotlinx.android.synthetic.main.list.view.*
import java.text.FieldPosition

class theAdapter(private val recyclerList: List<NotesData>, var clickListener: onItemClickListener): RecyclerView.Adapter<theAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun getItemCount() = recyclerList.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.initialize(recyclerList.get(position), clickListener)
    }
    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val noteTitle: TextView = itemView.the_Note

        fun initialize(item: NotesData, action: onItemClickListener){
            noteTitle.text = item.title

            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }
        }
    }
}
interface onItemClickListener{
    fun onItemClick(item: NotesData, position: Int)
}