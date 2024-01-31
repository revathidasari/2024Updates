package com.example.a2024updates.ui.home.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [MonthlyCategories::class],
    version = 4,
    exportSchema = false
)
abstract class MonthlyCategoriesDatabase : RoomDatabase() {

    abstract fun mCategoryDao() : MCategoryDao

    companion object {

        private var INSTANCE: MonthlyCategoriesDatabase? = null


        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Your migration logic here
                // For example, if you are adding a new column:
                database.execSQL("ALTER TABLE monthly_categories ADD COLUMN extra INTEGER NOT NULL DEFAULT 0")

                // Correct data migration logic
               // database.execSQL("UPDATE monthly_categories SET total_exp = 0")

                // Migrate data from the old schema to the new one
               // database.execSQL("INSERT INTO monthly_categories (total_exp) SELECT month_name FROM monthly_categories")
            }
        }


        fun getDBInstance(context: Context) : MonthlyCategoriesDatabase? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context,
                        MonthlyCategoriesDatabase::class.java,
                        "MonthlyCategoriesDatabase")
                        .addMigrations(MIGRATION_3_4)
                        //  .addMigrations(MIGRATION_1_2, MIGRATION_2_3, /* other migrations */)
                        //  .fallbackToDestructiveMigration()//table will be dropped on upgrade
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }


//not using as leading to error and not working
    private var MIGRATION_CURRENT_TO_NEXT = object : Migration(2,3) {
        override fun migrate(db: SupportSQLiteDatabase) {
            // Execute the ALTER TABLE query to add new columns
            val alterTableQuery = "ALTER TABLE monthly_categories " +
                    "ADD COLUMN get_from_others INTEGER, " +
                    "ADD COLUMN me_others_expense INTEGER, " +
                    "ADD COLUMN spending_others INTEGER"
            db.execSQL(alterTableQuery)
            //  db.execSQL("ALTER TABLE monthly_categories ADD get_from_others INTEGER, ADD me_others_expense INTEGER, ADD spending_others INTEGER")
            //ALTER TABLE monthly_categories ADD get_from_others INTEGER, ADD me_others_expense INTEGER, ADD spending_others INTEGER;
        }

    }

}