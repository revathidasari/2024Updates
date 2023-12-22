package com.example.a2024updates.ui.notes.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    var title: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
