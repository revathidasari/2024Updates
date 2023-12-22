package com.example.a2024updates.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2024updates.ui.home.MCategoryRepository
import com.example.a2024updates.ui.home.roomdb.MonthlyCategories

class DashboardViewModel : ViewModel() {

    private var readAll : List<MonthlyCategories> = emptyList()

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text


    fun getAllData(mCategoryRepository: MCategoryRepository): List<MonthlyCategories> {
        readAll = mCategoryRepository.getAllData()
        return readAll
    }

}