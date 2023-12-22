package com.example.a2024updates.ui.journal.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Journal::class],
    version = 1,
    exportSchema = false
)
abstract class JournalDatabase : RoomDatabase() {

    abstract fun journalDao() : JournalDao

    companion object {

        @Volatile
        private var INSTANCE: JournalDatabase? = null

        fun getDBInstance(context: Context) : JournalDatabase {
            return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context,
                        JournalDatabase::class.java,
                        "JournalDatabase")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()

                INSTANCE = instance

                instance
            }
        }
    }

}