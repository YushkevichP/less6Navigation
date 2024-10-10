package com.example.less6navigation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.less6navigation.databinding.ItemNoteBinding

class NotesAdapter(
    private val itemsList: List<NoteItem>,
    private val itemClick: (NoteItem) -> Unit
) : RecyclerView.Adapter<NotesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val myInflater = LayoutInflater.from(parent.context)
        return NotesViewHolder(
            itemBinding = ItemNoteBinding.inflate(myInflater, parent, false),
            itemClick = itemClick
        )
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }
}

class NotesViewHolder(
    private val itemBinding: ItemNoteBinding,
    private val itemClick: (NoteItem) -> Unit
) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(item: NoteItem) {
        itemBinding.textNote.text = item.noteText
        itemBinding.root.setOnClickListener {
            itemClick(item)
        }
    }
}