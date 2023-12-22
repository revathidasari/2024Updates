package com.example.a2024updates.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2024updates.R
import com.example.a2024updates.databinding.ActivityCategorizedViewsBinding
import com.example.a2024updates.ui.home.roomdb.MCategoryDao
import com.example.a2024updates.ui.home.roomdb.MonthlyCategories
import com.example.a2024updates.ui.home.roomdb.MonthlyCategoriesDatabase

class CategorizedViewsActivity : AppCompatActivity() {

    private var binding : ActivityCategorizedViewsBinding? = null
    private var categorizedViewsAdapter: CategorizedViewsAdapter? = null
    private var categories: ArrayList<String>? = arrayListOf()
    private var currentMonth: String? = null
    private var monthCategory :MonthlyCategories? = null


    private var categoriesDatabase: MonthlyCategoriesDatabase? = null
    private var mCategoryDao: MCategoryDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategorizedViewsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        categoriesDatabase = MonthlyCategoriesDatabase.getDBInstance(this)
        mCategoryDao = categoriesDatabase?.mCategoryDao()


        currentMonth = intent.getStringExtra("month")
        binding?.selectedMonthText?.text = currentMonth
        categories = resources.getStringArray(R.array.categories).toCollection(ArrayList())

        getCategoriesDataForSpecificMonth()

        if (monthCategory == null) {
           monthCategory = MonthlyCategories("",0,0,0,0,0,0, 0,
            0,0,0,0,0,0, 0,
            0, 0,0,0,0,0,0,0)
        }
        //Toast.makeText(this, "categories $monthCategory", Toast.LENGTH_SHORT).show()
       Log.d("revathi", "monthCategory $monthCategory")
        categorizedViewsAdapter = CategorizedViewsAdapter(
                this, currentMonth!!, categories!!, monthCategory)


      //  Log.d("revathi", "categories ${mCategoryDao?.getMonth(currentMonth!!)}")

        binding?.monthlyRecyclerView?.adapter = categorizedViewsAdapter
        binding?.monthlyRecyclerView?.layoutManager = LinearLayoutManager(this)

    }

    private fun getCategoriesDataForSpecificMonth() {
        val allData = mCategoryDao?.getAllData()

        if (allData != null) {
            for (category in allData) {
                Log.d("revathi", "All data   category $category")
                if (category.monthName == currentMonth) {
                    Log.d("revathi", "categories in get all ${mCategoryDao?.getMonth(currentMonth!!)}")
                    monthCategory = category
                    categorizedViewsAdapter?.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("revathi", "resume   monthCategory $monthCategory")

        getCategoriesDataForSpecificMonth()
        Log.d("revathi", "resume 11111  monthCategory $monthCategory")

        categorizedViewsAdapter?.updateItems(monthCategory!!)
       // categorizedViewsAdapter?.notifyDataSetChanged()

       // binding?.monthlyRecyclerView?.adapter = categorizedViewsAdapter
        /*getCategoriesDataForSpecificMonth()
        categorizedViewsAdapter = CategorizedViewsAdapter(
            this, currentMonth!!, categories!!, monthCategory)*/
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}