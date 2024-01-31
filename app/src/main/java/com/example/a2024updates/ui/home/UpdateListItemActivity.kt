package com.example.a2024updates.ui.home

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a2024updates.R
import com.example.a2024updates.ui.home.roomdb.MCategoryDao
import com.example.a2024updates.ui.home.roomdb.MonthlyCategories
import com.example.a2024updates.ui.home.roomdb.MonthlyCategoriesDatabase

class UpdateListItemActivity : AppCompatActivity() {

    var categoryAmount: Int = 0
    var currentMonth: TextView? = null
    var currentCategory: TextView? = null
    var currentAmount: EditText? = null
    var updatedAmount: EditText? = null
    var subButton: Button? = null
    var addButton: Button? = null
    var saveButton: Button? = null


    var salary = 0
    var fdInterest = 0
    var otherIncome = 0
    var loanEmi = 0
    var utilities = 0
    var recharge = 0
    var food = 0
    var transportation = 0
    var rent = 0
    var shopping = 0
    var medicalHealth = 0
    var personalCare = 0
    var entertainment = 0
    var otherExpenses = 0
    var loanRepay = 0
    var monthly14 = 0
    var daily10 = 0
    var homeGiving = 0
    var investment = 0
    var gift = 0
    var otherSavings = 0
    var paybackToOthers = 0
    var getFromOthers = 0
    var meAndOthersExpense = 0
    var spendingOnOthers = 0
    var totalIncome = 0
    var totalExpense = 0
    var totalSavings = 0
    var totalBalance = 0
    var extra = 0

    private var monthlyCategories: MonthlyCategories? = null
    private var categoriesDatabase: MonthlyCategoriesDatabase? = null
    private var mCategoryDao: MCategoryDao? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_list_item)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        currentMonth = findViewById(R.id.monthName)
        currentCategory = findViewById(R.id.selectedCategory)
        currentAmount = findViewById(R.id.amount1)
        updatedAmount = findViewById(R.id.updateAmount1)
        subButton = findViewById(R.id.buttonSub)
        addButton = findViewById(R.id.buttonAdd)
        saveButton = findViewById(R.id.buttonSave)

        currentMonth?.text = intent.getStringExtra("month").toString()
        currentCategory?.text = intent.getStringExtra("category").toString()
        categoryAmount = intent.getIntExtra("amount",0)
        currentAmount?.setText(categoryAmount.toString())


        subButton?.setOnClickListener {

            if (updatedAmount?.text.toString().isNullOrEmpty()) {
                updatedAmount?.setText("0")
            }
            currentAmount?.setText((currentAmount?.text.toString().toInt() - updatedAmount?.text.toString().toInt()).toString())
        }

        addButton?.setOnClickListener {

            if (updatedAmount?.text.toString().isNullOrEmpty()) {
                updatedAmount?.setText("0")
            }
            currentAmount?.setText((currentAmount?.text.toString().toInt() + updatedAmount?.text.toString().toInt()).toString())
        }

        categoriesDatabase = MonthlyCategoriesDatabase.getDBInstance(this)
        mCategoryDao = categoriesDatabase?.mCategoryDao()



        saveButton?.setOnClickListener {

                monthlyCategories?.monthName = currentMonth?.text.toString()

            //    monthlyCategories?.food = currentAmount?.text.toString().toInt()
           /* for (category in MonthlyCategoriesDatabase.getDBInstance(this)?.mCategoryDao()?.getAllUsers()!!) {
                if (category.monthName == currentMonth?.text.toString()) {
                    when(category) {
                        currentCategory -> {
                            monthlyCategories?.food = currentAmount?.text.toString().toInt()
                        }
                    }

                    if (category.equals(currentCategory)) {

                    }
                    monthlyCategories?.id = category.id
                    monthlyCategories?.salary = category.salary
                    monthlyCategories?.rent = category.rent
                    monthlyCategories?.grocery = category.grocery
                    monthlyCategories?.electricity = category.electricity
                    monthlyCategories?.water = category.water
                    monthlyCategories?.internet = category.internet
                    monthlyCategories?.phone = category.phone
                    monthlyCategories?.fuel = category.fuel
                    monthlyCategories?.shopping = category.shopping
                    monthlyCategories?.entertainment = category.entertainment
                    monthlyCategories?.health = category.health
                    monthlyCategories?.education = category.education
                    monthlyCategories?.travel = category.travel
                    monthlyCategories?.loan = category.loan
                    monthlyCategories?.insurance = category.insurance
                    monthlyCategories?.tax = category.tax
                    monthlyCategories?.investment = category.investment
                    monthlyCategories?.other = category.other
                    monthlyCategories?.total = category.total
                }
            }

            */
            val currentData = mCategoryDao?.getMonth(currentMonth?.text.toString())
            Log.d("revathi","currentData.toString() $currentData")
            if (currentData != null && currentData.monthName == currentMonth?.text.toString()) {
                salary = currentData.salary
               // monthlyCategories?.fdInterest
                fdInterest = currentData.fdInterest
                otherIncome = currentData.otherIncome
                loanEmi = currentData.loanEmi
                utilities = currentData.utilities
                recharge = currentData.recharge
                food = currentData.food
                transportation = currentData.transportation
                rent = currentData.rent
                shopping = currentData.shopping
                medicalHealth = currentData.medicalHealth
                personalCare = currentData.personalCare
                entertainment = currentData.entertainment
                otherExpenses = currentData.otherExpenses
                loanRepay = currentData.loanRepay
                monthly14 = currentData.monthly14
                daily10 = currentData.daily10
                homeGiving = currentData.homeGiving
                investment = currentData.investments
                gift = currentData.gift
                otherSavings = currentData.otherSavings
                paybackToOthers = currentData.paybackToOthers
                getFromOthers = currentData.getFromOthers
                meAndOthersExpense = currentData.meAndOthersExpense
                spendingOnOthers = currentData.spendingOnOthers
                totalIncome = currentData.totalIncome
                totalExpense = currentData.totalExpenses
                extra = currentData.extra
                Log.d("revathi","currentData.toString() ${currentData.salary} ${monthlyCategories?.salary} ${monthlyCategories?.loanEmi}")

            }

            when(currentCategory?.text.toString()) {
                "Salary" -> {
                    salary = currentAmount?.text.toString().toInt()
                    Log.d("revathi","whennnn  currentData.toString() ${currentData?.salary} ${monthlyCategories?.salary}")
                    /* monthlyCategories?.salary = salary
                     monthlyCategories?.let { it1 -> mCategoryDao?.insert(it1) }*/
                }
                "FD Interest" -> {
                    fdInterest = currentAmount?.text.toString().toInt()
                }
                "Other Income" -> {
                    otherIncome = currentAmount?.text.toString().toInt()
                }
                "Loan EMI" -> {
                    loanEmi = currentAmount?.text.toString().toInt()
                }
                "Utilities" -> {
                    utilities = currentAmount?.text.toString().toInt()
                }
                "Recharge" -> {
                    recharge = currentAmount?.text.toString().toInt()
                }
                "Food" -> {
                    food = currentAmount?.text.toString().toInt()
                }
                "Transportation" -> {
                    transportation = currentAmount?.text.toString().toInt()
                }
                "Rent" -> {
                    rent = currentAmount?.text.toString().toInt()
                }
                "Shopping" -> {
                    shopping = currentAmount?.text.toString().toInt()
                }
                "Medical and Health" -> {
                    medicalHealth = currentAmount?.text.toString().toInt()
                }
                "Personal Care" -> {
                    personalCare = currentAmount?.text.toString().toInt()
                }
                "Entertainment" -> {
                    entertainment = currentAmount?.text.toString().toInt()
                }
                "Other Expenses" -> {
                    otherExpenses = currentAmount?.text.toString().toInt()
                }
                "Loan Repay" -> {
                    loanRepay = currentAmount?.text.toString().toInt()
                }
                "Monthly(14%)" -> {
                    monthly14 = currentAmount?.text.toString().toInt()
                }
                "Daily(10Rs)" -> {
                    daily10 = currentAmount?.text.toString().toInt()
                }
                "Home Giving" -> {
                    homeGiving = currentAmount?.text.toString().toInt()
                }
                "Investment" -> {
                    investment = currentAmount?.text.toString().toInt()
                }
                "Gift" -> {
                    gift = currentAmount?.text.toString().toInt()
                }
                "Other Savings" -> {
                    otherSavings = currentAmount?.text.toString().toInt()
                }
                "Payback to Others" -> {
                    paybackToOthers = currentAmount?.text.toString().toInt()
                }
                "Get from Others"-> {
                    getFromOthers = currentAmount?.text.toString().toInt()
                }
                "Me and Others Expense" -> {
                    meAndOthersExpense = currentAmount?.text.toString().toInt()
                }
                "Spending on Others" -> {
                    spendingOnOthers = currentAmount?.text.toString().toInt()
                }
                "Extra" -> {
                    extra = currentAmount?.text.toString().toInt()
                }
            }

            Log.d("revathi","checkkk $salary $mCategoryDao $monthlyCategories")

            totalIncome = salary + fdInterest + otherIncome + getFromOthers
            totalExpense = loanEmi + utilities + recharge +
                    food+ transportation + rent + shopping + medicalHealth + personalCare +
                    entertainment + otherExpenses +homeGiving + investment + gift + paybackToOthers +
                    meAndOthersExpense + spendingOnOthers + extra
            totalSavings = loanRepay + monthly14 + daily10 + otherSavings

            totalBalance = totalIncome - totalExpense - totalSavings

            mCategoryDao?.insert(
                MonthlyCategories(
                    monthName = currentMonth?.text.toString(),
                    salary = salary,
                    fdInterest = fdInterest,
                    otherIncome = otherIncome,
                    loanEmi = loanEmi,
                    utilities = utilities,
                    recharge = recharge,
                    food = food,
                    transportation = transportation,
                    rent = rent,
                    shopping = shopping,
                    medicalHealth = medicalHealth,
                    personalCare = personalCare,
                    entertainment = entertainment,
                    otherExpenses = otherExpenses,
                    investments = investment,
                    loanRepay = loanRepay,
                    monthly14 = monthly14,
                    daily10 = daily10,
                    homeGiving = homeGiving,
                    gift = gift,
                    otherSavings = otherSavings,
                    paybackToOthers = paybackToOthers,
                    getFromOthers = getFromOthers,
                    meAndOthersExpense = meAndOthersExpense,
                    spendingOnOthers = spendingOnOthers,
                    totalIncome = totalIncome,
                    totalExpenses = totalExpense,
                    extra = extra
                ))


            monthlyCategories?.let {
                Log.d("revathi","in condition ${currentAmount?.text.toString().toInt()} ${currentCategory?.text.toString()}" +
                        "${monthlyCategories.toString()} ${monthlyCategories?.loanEmi} $totalSavings $totalBalance" )
                mCategoryDao?.insert(it)
          /*      it.categoryName = currentCategory?.text.toString()
                it.amount = currentAmount?.text.toString().toInt()*/
            }
                Log.d("revathi","user ${currentAmount?.text.toString().toInt()} ${currentCategory?.text.toString()}" +
                        "${monthlyCategories.toString()}")
           // Toast.makeText(this, "Food updated ${monthlyCategories.toString()}", Toast.LENGTH_SHORT).show()
            Log.d("revathi","getall ${mCategoryDao?.getMonth(currentMonth?.text.toString())}")

            finish()

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}