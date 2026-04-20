package com.example.naylaapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.naylaapps.databinding.ActivityAuthBinding
import com.example.naylaapps.pertemuan_2.SecondActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        //kode ini harus selalu dipanggil; saat butuh akses user_pref
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)


        binding.btnlogin.setOnClickListener {
            val username = binding.editTextText.text.toString()
            val password = binding.editTextTextPassword2.text.toString()
            if (username == password){

                sharedPref.edit() {
                    putBoolean("isLogin", true)
                    putString("username", username)
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }else
                Snackbar.make(binding.root, "Silahkan coba lagi", Snackbar.LENGTH_SHORT)
                    .show()
        }


    }
}