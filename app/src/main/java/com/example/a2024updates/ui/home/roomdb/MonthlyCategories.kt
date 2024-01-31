package com.example.a2024updates.ui.home.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monthly_categories")
data class MonthlyCategories(

    @PrimaryKey
    @ColumnInfo(name = "month_name")
    var monthName: String,

    /* Income */
    @ColumnInfo(name = "salary")
    var salary: Int,

    @ColumnInfo(name = "fd_interest")
    var fdInterest: Int,

    @ColumnInfo(name = "other_income")
    var otherIncome: Int,

    /* Expenses */
    @ColumnInfo(name = "loan_emi")
    var loanEmi: Int,

    @ColumnInfo(name = "utilities")
    var utilities: Int,

    @ColumnInfo(name = "recharge")
    var recharge: Int,

    @ColumnInfo(name = "food")
    var food: Int,

    @ColumnInfo(name = "transport")
    var transportation: Int,

    @ColumnInfo(name = "rent")
    var rent: Int,

    @ColumnInfo(name = "shopping")
    var shopping: Int,

    @ColumnInfo(name = "medical_health")
    var medicalHealth: Int,

    @ColumnInfo(name = "personal_care")
    var personalCare: Int,

    @ColumnInfo(name = "entertainment")
    var entertainment: Int,

    @ColumnInfo(name = "other_expenses")
    var otherExpenses: Int,

    @ColumnInfo(name = "investments")
    var investments: Int,

    /* Savings */
    @ColumnInfo(name = "loan_repay")
    var loanRepay: Int,

    @ColumnInfo(name = "monthly14")
    var monthly14: Int,

    @ColumnInfo(name = "daily10")
    var daily10: Int,

    /* Transfer*/
    @ColumnInfo(name = "home_giving")
    var homeGiving: Int,

    @ColumnInfo(name = "gift")
    var gift: Int,

    @ColumnInfo(name = "other_savings")
    var otherSavings: Int,

    @ColumnInfo(name = "payback_others")
    var paybackToOthers: Int,

    @ColumnInfo(name = "get_from_others")
    var getFromOthers: Int,

    @ColumnInfo(name = "me_others_expense")
    var meAndOthersExpense: Int,

    @ColumnInfo(name = "spending_others")
    var spendingOnOthers: Int,

    @ColumnInfo(name = "total_income")
    var totalIncome: Int,

    @ColumnInfo(name = "total_expenses")
    var totalExpenses: Int

)
