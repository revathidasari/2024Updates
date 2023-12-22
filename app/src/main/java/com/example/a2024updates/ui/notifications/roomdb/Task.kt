package com.example.a2024updates.ui.notifications.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    var taskText: String,
    var timeText: String,
    var notifyImg: Int,
    var taskDone: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
