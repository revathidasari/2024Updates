package com.example.a2024updates.ui.journal.roomdb

import androidx.lifecycle.LiveData

class JournalRepository (
    private val journalDao: JournalDao) {

    val allJournal : LiveData<List<Journal>> = journalDao.getAllJournals()

    suspend fun insert(journal: Journal) {
        journalDao.insert(journal)
    }

    suspend fun delete(journal: Journal) {
        journalDao.delete(journal)
    }

    suspend fun update(journal: Journal) {
        journalDao.update(journal.id, journal.title, journal.journal, journal.date)
    }
}