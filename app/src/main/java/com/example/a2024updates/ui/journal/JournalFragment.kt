package com.example.a2024updates.ui.journal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.a2024updates.R
import com.example.a2024updates.databinding.FragmentJournalBinding
import com.example.a2024updates.ui.journal.roomdb.Journal
import com.example.a2024updates.ui.journal.roomdb.JournalAdapter
import com.example.a2024updates.ui.journal.roomdb.JournalDao
import com.example.a2024updates.ui.journal.roomdb.JournalDatabase
import com.example.a2024updates.ui.journal.roomdb.JournalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JournalFragment : Fragment(), JournalAdapter.JournalClickListener, PopupMenu.OnMenuItemClickListener {

    private var _binding: FragmentJournalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var database: JournalDatabase
    lateinit var journalDao: JournalDao
    lateinit var journalRepository: JournalRepository
    lateinit var journalViewModel: JournalViewModel
    lateinit var journalAdapter: JournalAdapter
    lateinit var selectedJournal: Journal


    private val updateJournal = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val journal = result.data?.getParcelableExtra<Journal>("journal")
            if (journal != null) {
                lifecycleScope.launch(Dispatchers.IO) {
                    journalRepository.update(journal)
                }
              //  journalViewModel.updateJournal(journal)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val journalViewModel =
            ViewModelProvider(this)[JournalViewModel::class.java]

        _binding = FragmentJournalBinding.inflate(inflater, container, false)
        val root: View = binding.root


        database = JournalDatabase.getDBInstance(requireContext())
        journalDao = database.journalDao()
        journalRepository = JournalRepository(journalDao)


        val textView: TextView = binding.textJournal
        binding.journalRecyclerView.setHasFixedSize(true)
        binding.journalRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        journalAdapter = JournalAdapter(requireContext(), this)

        binding.journalRecyclerView.adapter = journalAdapter

        val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val journal = result.data?.getParcelableExtra<Journal>("journal")
                if (journal != null) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        journalRepository.insert(journal)
                    }
//                    journalViewModel.insertJournal(journal)
                }
            }
        }

        binding.addJournalFab.setOnClickListener {
            val intent = Intent(requireContext(), AddJournalActivity::class.java)
            getContent.launch(intent)
        }

        binding.searchJournal.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText!= null){
                    journalAdapter.filterList(newText)
                }
                return true
            }

        })


        journalViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

//        journalViewModel.allJournal.observe(viewLifecycleOwner) { list->
//            list?.let {
//                journalAdapter.updateList(it)
//            }
//        }

        lifecycleScope.launch(Dispatchers.Main) {
            journalRepository.allJournal.observe(viewLifecycleOwner) { list ->
                list?.let {
                    journalAdapter.updateList(it)
                }
            }
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onJournalItemClicked(journal: Journal) {
        val intent = Intent(requireContext(), AddJournalActivity::class.java)
        intent.putExtra("currentJournal", journal)
        updateJournal.launch(intent)
    }

    override fun onJournalItemLongClicked(journal: Journal, cardView: CardView) {
        selectedJournal = journal
        popUpDisplay(cardView)
    }

    private fun popUpDisplay(cardView: CardView) {
        val popupMenu = PopupMenu(requireContext(), cardView)
        popupMenu.setOnMenuItemClickListener(this)
        popupMenu.inflate(R.menu.pop_up_menu)
        popupMenu.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
            when(item?.itemId){
                R.id.deleteJournal -> {
                    lifecycleScope.launch(Dispatchers.IO) {
                        journalRepository.delete(selectedJournal)
                    }
                    return true
                }
            }
        return false
    }


}