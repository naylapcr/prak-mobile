package com.example.naylaapps.Note

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.naylaapps.data.local.AppDatabase
import com.example.naylaapps.data.local.Note
import com.example.naylaapps.databinding.ActivityNoteListBinding
import kotlinx.coroutines.launch

class NoteListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteListBinding
    private lateinit var adapter: NoteAdapter
    private val database by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, NoteFormActivity::class.java)
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
        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.adapter = adapter
    }

    private fun loadNotes() {
        lifecycleScope.launch {
            val notes = database.noteDao().getAllNotes()
            adapter.updateNotes(notes)
        }
    }

    private fun deleteNote(note: Note) {
        lifecycleScope.launch {
            database.noteDao().deleteNote(note)
            loadNotes()
        }
    }
}
