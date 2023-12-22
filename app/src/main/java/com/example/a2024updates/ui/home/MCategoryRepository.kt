package com.example.a2024updates.ui.home

import com.example.a2024updates.ui.home.roomdb.MCategoryDao
import com.example.a2024updates.ui.home.roomdb.MonthlyCategories

class MCategoryRepository(private val mCategoryDao: MCategoryDao) {

    fun insert(monthlyCategories: MonthlyCategories) {
        mCategoryDao.insert(monthlyCategories)
    }

    fun update(monthlyCategories: MonthlyCategories) {
        mCategoryDao.update(monthlyCategories)
    }

    fun delete(monthlyCategories: MonthlyCategories) {
        mCategoryDao.delete(monthlyCategories)
    }

    fun getAllData(): List<MonthlyCategories> {
        return mCategoryDao.getAllData()
    }
}