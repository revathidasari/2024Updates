package com.example.a2024updates.ui.notifications.indetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.a2024updates.R
import com.example.a2024updates.ui.notes.roomdb.NoteDatabase
import com.example.a2024updates.ui.notifications.roomdb.Task
import com.example.a2024updates.ui.notifications.roomdb.TaskDatabase

class NotifyAdapter(
    private val context: Context,
    private val taskList: ArrayList<Task>
) : RecyclerView.Adapter<NotifyAdapter.NotifyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotifyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
        return NotifyViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotifyViewHolder, position: Int) {
        val currentNotify = taskList[position]
/*
        holder.cardView.setOnClickListener {
            taskList[position].taskDone = !taskList[position].taskDone
            notifyItemChanged(position)
        }
*/

        holder.taskText.text = currentNotify.taskText
        holder.timeText.text = currentNotify.timeText
        holder.notifyImg.setImageResource(currentNotify.notifyImg)
        holder.taskDone.isChecked = currentNotify.taskDone
   /*     holder.taskDone.setOnLongClickListener {
            taskList.removeAt(position)
            notifyItemRemoved(position)
            true
        }*/


        holder.cardView.setOnLongClickListener {
            if (holder.taskDone.isChecked) {
                val taskDao = TaskDatabase.getInstance(context)?.taskDao()
                taskList.remove(currentNotify)
                notifyDataSetChanged()
                taskDao?.delete(currentNotify)
            }
            true
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }


    class NotifyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cardView: CardView = itemView.findViewById(R.id.notify_item_card)
        val taskText: TextView = itemView.findViewById(R.id.taskText)
        val timeText: TextView = itemView.findViewById(R.id.timeText)
        val notifyImg: ImageView = itemView.findViewById(R.id.notifyImg)
        val taskDone: SwitchCompat = itemView.findViewById(R.id.taskSwitch)
    }


}