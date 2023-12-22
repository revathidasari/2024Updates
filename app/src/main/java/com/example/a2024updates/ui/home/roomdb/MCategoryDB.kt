package com.example.a2024updates.ui.home.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [MonthlyCategories::class],
    version = 1,
    exportSchema = false
)
abstract class MonthlyCategoriesDatabase : RoomDatabase() {

    abstract fun mCategoryDao() : MCategoryDao

    companion object {

        private var INSTANCE: MonthlyCategoriesDatabase? = null

        fun getDBInstance(context: Context) : MonthlyCategoriesDatabase? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context,
                        MonthlyCategoriesDatabase::class.java,
                        "MonthlyCategoriesDatabase")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }

}