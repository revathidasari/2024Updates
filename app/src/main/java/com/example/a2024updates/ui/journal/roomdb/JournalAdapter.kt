package com.example.a2024updates.ui.journal.roomdb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.a2024updates.R
import kotlin.random.Random

class JournalAdapter(
    private val context: Context,
    val listener: JournalClickListener
): RecyclerView.Adapter<JournalAdapter.JournalViewHolder>() {

    private val journalList = ArrayList<Journal>()
    private val fullList = ArrayList<Journal>()


    inner class JournalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val journalLayout: CardView = itemView.findViewById(R.id.journalCardView)
        val title: TextView = itemView.findViewById(R.id.journalTitle)
        val journal: TextView = itemView.findViewById(R.id.journaldescription)
        val date: TextView = itemView.findViewById(R.id.journalDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
        return JournalViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_journal, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return journalList.size
    }

    override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
        val currentJournal = journalList[position]
        holder.title.text = currentJournal.title
        holder.title.isSelected = true
        holder.journal.text = currentJournal.journal
        holder.date.text = currentJournal.date
        holder.date.isSelected = true

        holder.journalLayout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(), null))

        holder.journalLayout.setOnClickListener {
            listener.onJournalItemClicked(journalList[holder.adapterPosition])
        }

        holder.journalLayout.setOnLongClickListener {
            listener.onJournalItemLongClicked(journalList[holder.adapterPosition], holder.journalLayout)
            true
        }
    }

    fun updateList(newList: List<Journal>) {

        fullList.clear()
        fullList.addAll(newList)

        journalList.clear()
        journalList.addAll(fullList)

        notifyDataSetChanged()
    }

    fun filterList(search: String) {
        journalList.clear()

        for (item in fullList) {
            if (item.title?.lowercase()?.contains(search.lowercase()) == true ||
                    item.journal?.lowercase()?.contains(search.lowercase()) == true) {
                journalList.add(item)
            }
        }
        notifyDataSetChanged()
    }


    private fun randomColor(): Int {
        val list = ArrayList<Int>()
        list.add(R.color.Color1)
        list.add(R.color.Color2)
        list.add(R.color.Color3)
        list.add(R.color.Color4)
        list.add(R.color.Color5)
        list.add(R.color.Color6)
        list.add(R.color.Color7)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]
    }

    interface JournalClickListener {
        fun onJournalItemClicked(journal: Journal)
        fun onJournalItemLongClicked(journal: Journal, cardView: CardView)
    }
}