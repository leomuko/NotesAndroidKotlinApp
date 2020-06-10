package com.example.notesapp.helpers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.database.NoteDatabaseModel
import kotlinx.android.synthetic.main.list.view.*
import java.util.*

class NoteAdapter internal constructor
    ( private val listener: OnItemClickListener) : RecyclerView.Adapter<NoteAdapter.RecyclerViewHolder>(), Filterable {

    private  var recyclerList: List<NoteDatabaseModel> = emptyList()
    private  var recyclerFilterList: List<NoteDatabaseModel> = emptyList()
    //private var clickListener: OnItemClickListener? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list, parent, false)
        return RecyclerViewHolder(itemView)
    }

    override fun getItemCount() = recyclerFilterList.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        listener.let { holder.initialize(recyclerFilterList[position], it) }
//        var currentNote: NoteDatabaseModel = recyclerList[position]
//        holder.noteTitle.text = currentNote.note_title
    }

    fun setNotes(notes: List<NoteDatabaseModel>) {
        this.recyclerList = notes
        notifyDataSetChanged()
        this.recyclerFilterList = recyclerList
    }

    fun getNoteAt(position: Int): NoteDatabaseModel{
        return recyclerFilterList[position]
    }


    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var noteTitle: TextView = itemView.the_Note
        var noteDetails: TextView = itemView.the_Note_details
        var noteLayout = itemView.note_layout

        fun initialize(item: NoteDatabaseModel, action: OnItemClickListener) {
            noteTitle.text = item.note_title
            noteDetails.text = item.note_details
            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }
        }


    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if(charSearch.isEmpty()){
                    recyclerFilterList = recyclerList
                } else{
                    val resultList  = arrayListOf<NoteDatabaseModel>()
                    for (row in recyclerList){
                        if (row.note_title.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(
                                Locale.ROOT)) || row.note_details.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(
                                Locale.ROOT)) ){
                            resultList.add(row)
                        }
                    }
                    recyclerFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = recyclerFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                recyclerFilterList = results?.values as List<NoteDatabaseModel>
                notifyDataSetChanged()
            }

        }
    }


}
interface OnItemClickListener {
    fun onItemClick(item: NoteDatabaseModel, position: Int)
}
