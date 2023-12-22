package com.example.a2024updates.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.a2024updates.R
import com.example.a2024updates.databinding.FragmentDashboardBinding
import com.example.a2024updates.ui.home.MCategoryRepository
import com.example.a2024updates.ui.home.roomdb.MCategoryDao
import com.example.a2024updates.ui.home.roomdb.MonthlyCategories
import com.example.a2024updates.ui.home.roomdb.MonthlyCategoriesDatabase
import com.example.a2024updates.util.ViewAsGraph


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var categoriesDatabase: MonthlyCategoriesDatabase? = null
    private var mCategoryDao: MCategoryDao? = null

    private var income =0
    private var expense = 0
    private var balance = 0
    private var currentMonth = ""
    private var totalBalanceAmount =""
    private var totalIncomeAmount =""
    private var totalExpenseAmount =""

    private var monthCategory : MonthlyCategories? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        categoriesDatabase = MonthlyCategoriesDatabase.getDBInstance(requireContext())
        mCategoryDao = categoriesDatabase?.mCategoryDao()
        val mCategoryRepository = MCategoryRepository(mCategoryDao!!)

        val spinner = binding.spinner
        val textView: TextView = binding.textDashboard
        totalBalanceAmount = binding.totalBalanceAmount.toString()
        totalIncomeAmount = binding.incomeLayout.incomeAmount.toString()
        totalExpenseAmount = binding.expenseLayout.incomeAmount.toString()


        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.months,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                p1: View?,
                position: Int,
                p3: Long
            ) {
                //Toast.makeText(requireContext(), parent?.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show()
                currentMonth = parent?.getItemAtPosition(position).toString()
                updateData(currentMonth)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //Toast.makeText(requireContext(), "Nothing selected", Toast.LENGTH_SHORT).show()
            }
        }

        binding.incomeLayout.incomeText.text = getString(R.string.income)
        binding.expenseLayout.incomeText.text = getString(R.string.expense)
        binding.expenseLayout.incomeIcon.setImageResource(R.drawable.baseline_arrow_upward_24)
        binding.expenseLayout.incomeIcon.setColorFilter(resources.getColor(R.color.red))

       //nr// dashboardViewModel.getAllData(mCategoryRepository)

        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    private fun calculateData(data: FloatArray): FloatArray {
        var total = 0f
        for (i in data.indices) {
            total += data[i]
        }
        for (i in data.indices) {
            data[i] = 360 * (data[i] / total)
        }
        return data
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun updateData(month: String) {

        mCategoryDao?.getMonth(month)?.let {
            monthCategory = it
            income = it.totalIncome
            expense = it.totalExpenses
        }

        //Toast.makeText(requireContext(), "income: $income, expense: $expense", Toast.LENGTH_SHORT).show()
        balance = income - expense

        binding.totalBalanceAmount.text = balance.toString()
        binding.incomeLayout.incomeAmount.text = income.toString()
        binding.expenseLayout.incomeAmount.text = expense.toString()

        if (income > 0) {
            val expensePercent = (expense * 100 / income)

           // Toast.makeText(requireContext(), "expensePercent: $expensePercent", Toast.LENGTH_SHORT).show()
            var values = floatArrayOf(
                expensePercent.toFloat(),
                100f - expensePercent.toFloat()
            )
            values = calculateData(values)
            binding.chartLayout.addView(ViewAsGraph(requireContext(), values))
        }

    }

}