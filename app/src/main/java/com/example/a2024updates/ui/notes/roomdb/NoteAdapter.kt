package com.example.a2024updates.ui.notes.roomdb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a2024updates.R

class NoteAdapter(
    private val context: Context,
    private val noteList: ArrayList<Note>
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val noteText: TextView = itemView.findViewById(R.id.noteItemText)
        val deleteNote: ImageButton = itemView.findViewById(R.id.deleteNote)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = noteList[position]
        holder.noteText.text = currentNote.title

        holder.deleteNote.setOnLongClickListener {
            val noteDao = NoteDatabase.getInstance(context)?.noteDao()
            noteList.remove(currentNote)
            notifyDataSetChanged()
            noteDao?.deleteNote(currentNote)
            true
        }
    }


}