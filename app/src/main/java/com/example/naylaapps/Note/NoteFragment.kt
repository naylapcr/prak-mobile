package com.example.naylaapps.Note

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.naylaapps.data.local.AppDatabase
import com.example.naylaapps.data.local.Note
import com.example.naylaapps.databinding.FragmentNoteBinding
import kotlinx.coroutines.launch

class NoteFragment : Fragment() {
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: NoteAdapter
    private val database by lazy { AppDatabase.getDatabase(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        binding.fabAdd.setOnClickListener {
            val intent = Intent(requireContext(), NoteFormActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        loadNotes()
    }

    private fun setupRecyclerView() {
        adapter = NoteAdapter(emptyList()) { note ->
            deleteNote(note)
        }
        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.adapter = adapter
    }

    private fun loadNotes() {
        viewLifecycleOwner.lifecycleScope.launch {
            val notes = database.noteDao().getAllNotes()
            adapter.updateNotes(notes)
        }
    }

    private fun deleteNote(note: Note) {
        viewLifecycleOwner.lifecycleScope.launch {
            database.noteDao().deleteNote(note)
            loadNotes()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
