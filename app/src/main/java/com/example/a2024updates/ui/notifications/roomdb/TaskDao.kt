package com.example.a2024updates.ui.notifications.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): List<Task>

    @Query("SELECT * FROM tasks WHERE taskText = :title")
    fun getTaskByTitle(title: String): Task

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getTaskById(id: Int): Task

    @Query("DELETE FROM tasks")
    fun deleteAllTasks()

}
