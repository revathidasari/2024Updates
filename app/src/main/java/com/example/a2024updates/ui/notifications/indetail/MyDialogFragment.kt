package com.example.a2024updates.ui.notifications.indetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.a2024updates.R
import com.example.a2024updates.databinding.MyDialogFragmentBinding

class MyDialogFragment : DialogFragment() {

    private lateinit var binding: MyDialogFragmentBinding

    //define the interface for passing data to the calling fragment
    interface DialogListener {
        fun onDataPassed(data: String, data1: String, dataImage: Int, taskDone: Boolean)
    }

    private var listener: DialogListener? = null
    private var currentImage: Int? = null

    fun setDialogListener(listener: DialogListener) {
        this.listener = listener
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = MyDialogFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.apply {
            categoriesImages.foodImg.setOnClickListener {
                Toast.makeText(context, "Food", Toast.LENGTH_SHORT).show()
               // categoriesImages.foodImg.id //not working thrown ad resource id not found
                currentImage = R.drawable.baseline_dining_24
            }
            categoriesImages.rechargeImg.setOnClickListener {
                Toast.makeText(context, "Recharge", Toast.LENGTH_SHORT).show()
                currentImage = R.drawable.baseline_phone_recharge
            }
            categoriesImages.transportationImg.setOnClickListener {
                Toast.makeText(context, "Transportation", Toast.LENGTH_SHORT).show()
                currentImage = R.drawable.baseline_transportation
            }
            categoriesImages.rentImg.setOnClickListener {
                Toast.makeText(context, "Rent", Toast.LENGTH_SHORT).show()
                currentImage = R.drawable.baseline_apartment_rent
            }
            categoriesImages.shoppingImg.setOnClickListener {
                Toast.makeText(context, "Shopping", Toast.LENGTH_SHORT).show()
                currentImage = R.drawable.baseline_shopping
            }
            categoriesImages.medicalHealthImg.setOnClickListener {
                Toast.makeText(context, "Medical and Health", Toast.LENGTH_SHORT).show()
                currentImage = R.drawable.baseline_health_and_safety_24
            }
            categoriesImages.personalCareImg.setOnClickListener {
                Toast.makeText(context, "Personal Care", Toast.LENGTH_SHORT).show()
                currentImage = R.drawable.personalcare
            }
            categoriesImages.entertainmentImg.setOnClickListener {
                Toast.makeText(context, "Entertainment", Toast.LENGTH_SHORT).show()
                currentImage = R.drawable.baseline_entertainment
            }
            categoriesImages.homeGivingImg.setOnClickListener {
                Toast.makeText(context, "Home giving", Toast.LENGTH_SHORT).show()
                currentImage = R.drawable.baseline_other_houses_24
            }
            categoriesImages.giftImg.setOnClickListener {
                Toast.makeText(context, "Gift", Toast.LENGTH_SHORT).show()
                currentImage = R.drawable.baseline_redeem_gift
            }
            categoriesImages.savingsImg.setOnClickListener {
                Toast.makeText(context, "Savings", Toast.LENGTH_SHORT).show()
                currentImage = R.drawable.baseline_savings_24
            }

        }
        binding.calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            val msg = "" + day + "/" + (month + 1) + "/" + year
            Toast.makeText(context, "Selected date is $msg", Toast.LENGTH_SHORT).show()
            binding.editNotifyTime.setText(msg)
        }

        val okBtn = binding.saveNotifyBtn
        okBtn.setOnClickListener {
            val data = binding.editNotifyTitle.text.toString()
            val data1 = binding.editNotifyTime.text.toString()
            val dataImage = currentImage
            //pass the data to the calling fragment
            listener?.onDataPassed(data, data1, dataImage?:0, false)
            //dismiss the dialog
            dismiss()
        }
        return view
    }


}