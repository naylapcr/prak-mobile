package com.example.naylaapps.pertemuan_5

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.naylaapps.R
import com.example.naylaapps.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi binding HARUS di paling atas
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Aktifkan mode layar penuh
        enableEdgeToEdge()

        // 3. Setup Toolbar (sekarang aman karena binding sudah di-inflate)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Web Merdeka"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // 4. Setup WebView
        binding.webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl("https://merdeka.com")
        }

        // 5. Setup Window Insets agar tidak tertutup Notch/Status Bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 6. Logika scroll untuk sembunyikan/tampilkan Toolbar
        binding.webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                binding.appBar.setExpanded(false, true) // Sembunyikan saat scroll ke bawah
            } else if (scrollY < oldScrollY) {
                binding.appBar.setExpanded(true, true)  // Tampilkan saat scroll ke atas
            }
        }
    }

    // 7. Menangani tombol Back agar tidak langsung keluar aplikasi
    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack() // Kembali ke halaman web sebelumnya
        } else {
            super.onBackPressed() // Keluar dari activity
        }
    }
}