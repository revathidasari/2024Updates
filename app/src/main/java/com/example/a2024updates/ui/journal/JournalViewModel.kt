package com.example.a2024updates.ui.journal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JournalViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is journal Fragment"
    }
    val text: LiveData<String> = _text


//    fun deleteJournal(journal: Journal) = viewModelScope.launch(Dispatchers.IO) {
//            journalRepository.delete(journal)
//        }
//
//    fun insertJournal(journal: Journal) = viewModelScope.launch(Dispatchers.IO) {
//            journalRepository.insert(journal)
//        }
//
//    fun updateJournal(journal: Journal) = viewModelScope.launch(Dispatchers.IO) {
//            journalRepository.update(journal)
//        }

}