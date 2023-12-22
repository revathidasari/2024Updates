package com.example.a2024updates

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class SplashLoginActivity : AppCompatActivity() {

    private lateinit var relativeLayout: RelativeLayout
    private var handler = Handler()
    private var runnable = Runnable {
        relativeLayout.visibility = View.VISIBLE
    }

    lateinit var sharedPreference: SharedPreferences
    private val APP_PREF = "appLoginData"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_login)

        relativeLayout = findViewById(R.id.relativeLayout)
        val password = findViewById<EditText>(R.id.passwordText)
        val submit = findViewById<Button>(R.id.submitButton)
        val forgotText = findViewById<TextView>(R.id.forgotPasswordText)

        sharedPreference = this.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE)

        forgotText.setOnClickListener {
           // Toast.makeText(this, "forgot", Toast.LENGTH_SHORT).show()
            showSingleButtonDialog(this, password.text.toString())
        }


        handler.postDelayed(runnable, 1000)


        submit.setOnClickListener {
            if (password.text.toString() == sharedPreference.getString("password","myself")) {
               // Toast.makeText(this, "submitted", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                password.setText("")
            }
        }
    }

    private fun showSingleButtonDialog(context: Context, data: String) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(data)
            .setPositiveButton("OK") { dialog, _ ->
                sharedPreference.edit().putString("password",data).apply()
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.show()
    }

}