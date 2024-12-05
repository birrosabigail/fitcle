package com.birros.eksportfigmafitcle

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class utamaew : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_utamaew)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val myImageView: ImageView = findViewById(R.id.imageView7)
        myImageView.setOnClickListener {
            // Pindah ke halaman SecondActivity
            val intent = Intent(this, now::class.java)
            startActivity(intent)
        }
        val myimageView: ImageView = findViewById(R.id.imageView9)
        myimageView.setOnClickListener {
            // Pindah ke halaman SecondActivity
            val intent = Intent(this, woo::class.java)
            startActivity(intent)
        }
        val myyimageView: ImageView = findViewById(R.id.imageView11)
        myyimageView.setOnClickListener {
            // Pindah ke halaman SecondActivity
            val intent = Intent(this, oo::class.java)
            startActivity(intent)
        }
        val myimaggeView: ImageView = findViewById(R.id.imageView13)
        myimaggeView.setOnClickListener {
            // Pindah ke halaman SecondActivity
            val intent = Intent(this, oow::class.java)
            startActivity(intent)
        }
        val myiiimaggeView: ImageView = findViewById(R.id.imageView18)
        myiiimaggeView.setOnClickListener {
            // Pindah ke halaman SecondActivity
            val intent = Intent(this, userzz::class.java)
            startActivity(intent)
        }
    }
}