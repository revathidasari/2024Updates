package com.example.a2024updates.ui.journal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.a2024updates.R
import com.example.a2024updates.databinding.ActivityAddJournalBinding
import com.example.a2024updates.ui.journal.roomdb.Journal
import java.text.SimpleDateFormat
import java.util.Date

class AddJournalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddJournalBinding

    private lateinit var journal: Journal
    private lateinit var oldJournal: Journal
    var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            oldJournal = intent.getParcelableExtra<Journal>("currentJournal")!!
            binding.editTitle.setText(oldJournal.title)
            binding.editDescription.setText(oldJournal.journal)
            isUpdate = true
        } catch (e: Exception) {
            isUpdate = false
        }

        binding.saveImg.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val description = binding.editDescription.text.toString()

            if(title.isNotEmpty() || description.isNotEmpty()) {
                val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss")
                if(isUpdate) {
                    journal = Journal(oldJournal.id, title, description, formatter.format(Date()))
                } else {
                    journal = Journal(null, title, description, formatter.format(Date()))
                }
                val intent = Intent()
                intent.putExtra("journal", journal)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

        binding.backImg.setOnClickListener {
            onBackPressed()
        }
    }

}