package com.birros.eksportfigmafitcle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class hommealone : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hommealone)

        // Mengatur padding agar layout tidak tertutup status bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Tombol gambar untuk ke halaman utama
        val myImageVview: ImageView = findViewById(R.id.imageView42)
        myImageVview.setOnClickListener {
            val intent = Intent(this, utamaew::class.java)
            startActivity(intent)
        }

        // Tombol "Tambah Buku"
        val btnGoToTmbh = findViewById<Button>(R.id.btnGoToTmbh)
        btnGoToTmbh.setOnClickListener {
            changeToTmbh()
        }
    }

    fun changeToTmbh() {
        val intent = Intent(this, TmbhActivity::class.java)
        startActivity(intent)
    }
}
