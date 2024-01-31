package com.example.a2024updates.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.a2024updates.R
import com.example.a2024updates.ui.home.roomdb.MonthlyCategories


class CategorizedViewsAdapter(
    private val context: Context,
    private val currentMonth: String,
    private val categoriesList: ArrayList<String>,
    private val monthCategory: MonthlyCategories?
) : RecyclerView.Adapter<CategorizedViewsAdapter.CategorizedViewHolder>() {


    class CategorizedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val category: TextView = itemView.findViewById(R.id.categoryText)
        val amount: TextView = itemView.findViewById(R.id.amountCategoryText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorizedViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.monthly_list_item_view, parent, false)
        return CategorizedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategorizedViewHolder, position: Int) {
        val category = categoriesList[position]
        holder.category.text = category
        holder.amount.text = when(category) {
            "Salary" -> monthCategory?.salary.toString()
            "FD Interest" -> monthCategory?.fdInterest.toString()
            "Other Income" -> monthCategory?.otherIncome.toString()
            "Loan EMI" -> monthCategory?.loanEmi.toString()
            "Utilities" -> monthCategory?.utilities.toString()
            "Recharge" -> monthCategory?.recharge.toString()
            "Food" -> monthCategory?.food.toString()
            "Transportation" -> monthCategory?.transportation.toString()
            "Rent" -> monthCategory?.rent.toString()
            "Shopping" -> monthCategory?.shopping.toString()
            "Medical and Health" -> monthCategory?.medicalHealth.toString()
            "Personal Care" -> monthCategory?.personalCare.toString()
            "Entertainment" -> monthCategory?.entertainment.toString()
            "Other Expenses" -> monthCategory?.otherExpenses.toString()
            "Loan Repay" -> monthCategory?.loanRepay.toString()
            "Monthly(14%)" -> monthCategory?.monthly14.toString()
            "Daily(10Rs)" -> monthCategory?.daily10.toString()
            "Home Giving" -> monthCategory?.homeGiving.toString()
            "Investment" -> monthCategory?.investments.toString()
            "Gift" -> monthCategory?.gift.toString()
            "Other Savings" -> monthCategory?.otherSavings.toString()
            "Payback to Others" -> monthCategory?.paybackToOthers.toString()
            "Get from Others" -> monthCategory?.getFromOthers.toString()
            "Me and Others Expense" -> monthCategory?.meAndOthersExpense.toString()
            "Spending on Others" -> monthCategory?.spendingOnOthers.toString()
            else -> "0"
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, UpdateListItemActivity::class.java)
            intent.putExtra("category", category)
            intent.putExtra("month", currentMonth)
            when(category){
                "Salary" -> intent.putExtra("amount", monthCategory?.salary)
                "FD Interest" -> intent.putExtra("amount", monthCategory?.fdInterest)
                "Other Income" -> intent.putExtra("amount", monthCategory?.otherIncome)
                "Loan EMI" -> intent.putExtra("amount", monthCategory?.loanEmi)
                "Utilities" -> intent.putExtra("amount", monthCategory?.utilities)
                "Recharge" -> intent.putExtra("amount", monthCategory?.recharge)
                "Food" -> intent.putExtra("amount", monthCategory?.food)
                "Transportation" -> intent.putExtra("amount", monthCategory?.transportation)
                "Rent" -> intent.putExtra("amount", monthCategory?.rent)
                "Shopping" -> intent.putExtra("amount", monthCategory?.shopping)
                "Medical and Health" -> intent.putExtra("amount", monthCategory?.medicalHealth)
                "Personal Care" -> intent.putExtra("amount", monthCategory?.personalCare)
                "Entertainment" -> intent.putExtra("amount", monthCategory?.entertainment)
                "Other Expenses" -> intent.putExtra("amount", monthCategory?.otherExpenses)
                "Loan Repay" -> intent.putExtra("amount", monthCategory?.loanRepay)
                "Monthly(14%)" -> intent.putExtra("amount", monthCategory?.monthly14)
                "Daily(10Rs)" -> intent.putExtra("amount", monthCategory?.daily10)
                "Home Giving" -> intent.putExtra("amount", monthCategory?.homeGiving)
                "Investment" -> intent.putExtra("amount", monthCategory?.investments)
                "Gift" -> intent.putExtra("amount", monthCategory?.gift)
                "Other Savings" -> intent.putExtra("amount", monthCategory?.otherSavings)
                "Payback to Others" -> intent.putExtra("amount", monthCategory?.paybackToOthers)
                "Get from Others" -> intent.putExtra("amount", monthCategory?.getFromOthers)
                "Me and Others Expense" -> intent.putExtra("amount", monthCategory?.meAndOthersExpense)
                "Spending on Others" -> intent.putExtra("amount", monthCategory?.spendingOnOthers)
                else -> intent.putExtra("amount", 0)
            }
           // Toast.makeText(context, "category $category ${monthCategory?.salary} ${monthCategory?.recharge}", Toast.LENGTH_SHORT).show()

            context.startActivity(intent)
        }
    }

    fun updateItems(monthCategory: MonthlyCategories) {
        this.monthCategory?.salary = monthCategory.salary
        this.monthCategory?.fdInterest = monthCategory.fdInterest
        this.monthCategory?.otherIncome = monthCategory.otherIncome
        this.monthCategory?.loanEmi = monthCategory.loanEmi
        this.monthCategory?.utilities = monthCategory.utilities
        this.monthCategory?.recharge = monthCategory.recharge
        this.monthCategory?.food = monthCategory.food
        this.monthCategory?.transportation = monthCategory.transportation
        this.monthCategory?.rent = monthCategory.rent
        this.monthCategory?.shopping = monthCategory.shopping
        this.monthCategory?.medicalHealth = monthCategory.medicalHealth
        this.monthCategory?.personalCare = monthCategory.personalCare
        this.monthCategory?.entertainment = monthCategory.entertainment
        this.monthCategory?.otherExpenses = monthCategory.otherExpenses
        this.monthCategory?.loanRepay = monthCategory.loanRepay
        this.monthCategory?.monthly14 = monthCategory.monthly14
        this.monthCategory?.daily10 = monthCategory.daily10
        this.monthCategory?.homeGiving = monthCategory.homeGiving
        this.monthCategory?.investments = monthCategory.investments
        this.monthCategory?.gift = monthCategory.gift
        this.monthCategory?.otherSavings = monthCategory.otherSavings
        this.monthCategory?.paybackToOthers = monthCategory.paybackToOthers
        this.monthCategory?.getFromOthers = monthCategory.getFromOthers
        this.monthCategory?.meAndOthersExpense = monthCategory.meAndOthersExpense
        this.monthCategory?.spendingOnOthers = monthCategory.spendingOnOthers
        notifyDataSetChanged()

    }


}
