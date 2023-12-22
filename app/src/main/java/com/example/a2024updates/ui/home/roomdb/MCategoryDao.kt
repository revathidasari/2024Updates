package com.example.a2024updates.ui.home.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(monthlyCategories: MonthlyCategories)

    @Update
    fun update(monthlyCategories: MonthlyCategories)

    @Query("select * from monthly_categories where month_name=:month")
    fun getMonth(month: String): MonthlyCategories

    @Delete
    fun delete(monthlyCategories: MonthlyCategories)

    @Query("select * from monthly_categories")
    fun getAllData(): List<MonthlyCategories>

    @Query("delete from monthly_categories")
    fun deleteAll()

}