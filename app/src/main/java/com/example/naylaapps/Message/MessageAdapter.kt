package com.example.naylaapps.Message

import android.R.id.message
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.naylaapps.databinding.ItemMessageBinding
import com.google.android.material.snackbar.Snackbar

class MessageAdapter(
    context: Context,
    private val Messages: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, Messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(context), parent, false)
        val data = Messages[position]

        // Menampilkan gambar menggunakan Glide
        Glide.with(context)
            .load(data.avatarUrl)
            .into(binding.avatarImg)

        binding.textSender.text = data.senderName
        binding.textMessage.text = data.messageText

        // Klik Item untuk menampilkan Snackbar
        binding.root.setOnClickListener {
            Snackbar.make(parent, "Pesan dari ${data.senderName}", Snackbar.LENGTH_SHORT).show()
        }

        return binding.root
    }
}