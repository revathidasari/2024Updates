package com.example.a2024updates.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a2024updates.databinding.FragmentNotesBinding
import com.example.a2024updates.ui.notes.roomdb.Note
import com.example.a2024updates.ui.notes.roomdb.NoteAdapter
import com.example.a2024updates.ui.notes.roomdb.NoteDao
import com.example.a2024updates.ui.notes.roomdb.NoteDatabase

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val TAG = "NotesFragment"
    var editNote: EditText? = null
    var addNote: Button? = null

    var currentNote : Note? = null
    val noteList: ArrayList<Note> = arrayListOf()
    var notesRecyclerView: RecyclerView? = null
    var noteAdapter: NoteAdapter? = null

    private var noteDatabase: NoteDatabase? = null
    private var noteDao: NoteDao? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notesViewModel =
            ViewModelProvider(this).get(NotesViewModel::class.java)

        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotes
        notesRecyclerView = binding.noteRecyclerView
        editNote = binding.editNote
        addNote = binding.addNote

        noteDatabase = NoteDatabase.getInstance(requireContext())
        noteDao = noteDatabase?.noteDao()

        noteAdapter = NoteAdapter(requireContext(), noteList)
        notesRecyclerView?.adapter = noteAdapter
        notesRecyclerView?.layoutManager = LinearLayoutManager(requireContext())



        notesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        getAllNotes()

        //noteDao?.deleteAllNotes()

        addNote?.setOnClickListener {

           // val noteDao = NoteDatabase.getInstance(requireContext())?.noteDao()
            currentNote = Note(editNote?.text.toString())
           // Toast.makeText(requireContext(), "Note Added $currentNote", Toast.LENGTH_SHORT).show()
            if (currentNote?.title?.isEmpty() == true) {
                Toast.makeText(requireContext(), "Note is Empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            currentNote?.let {
                noteList.add(it)
                noteAdapter?.notifyDataSetChanged()
                noteDao?.insertNote(it)
            }
            editNote?.setText("")

        }

        return root
    }

    private fun getAllNotes() {
        noteDao?.getAllNotes()?.let { noteList.addAll(it) }
        noteAdapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}