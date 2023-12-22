package com.example.a2024updates.ui.journal.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JournalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(journal: Journal)

    @Delete
    suspend fun delete(journal: Journal)

    @Query("SELECT * FROM journal ORDER BY id ASC")
    fun getAllJournals() : LiveData<List<Journal>>

    @Query("UPDATE journal SET title = :title, journal = :journal, date = :date WHERE id = :id")
    suspend fun update(id: Int?, title: String?, journal: String?, date: String?)

}