package com.birros.eksportfigmafitcle

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.birros.eksportfigmafitcle.model.Book
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore

class InputBukuActivity : AppCompatActivity() {
    private lateinit var etJudulBuku: EditText
    private lateinit var etGenre: EditText
    private lateinit var etSinopsis: EditText
    private lateinit var btnSimpanBuku: Button
    private lateinit var etGambar: EditText
    private lateinit var imgPreview: ImageView
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_buku)

        etJudulBuku = findViewById(R.id.etJudulBuku)
        etGenre = findViewById(R.id.etGenre)
        etSinopsis = findViewById(R.id.etSinopsis)
        btnSimpanBuku = findViewById(R.id.btnSimpanBuku)
        etGambar = findViewById(R.id.etGambar)
        imgPreview = findViewById(R.id.imgPreview)

        btnSimpanBuku.setOnClickListener {
            saveBook()
        }

        etGambar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val url = s.toString()
                if (url.isNotEmpty()) {
                    Glide.with(this@InputBukuActivity)
                        .load(url)
                        .into(imgPreview)
                } else {
                    imgPreview.setImageResource(R.drawable.ic_launcher_background)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun saveBook() {
        val judul = etJudulBuku.text.toString().trim()
        val genre = etGenre.text.toString().trim()
        val sinopsis = etSinopsis.text.toString().trim()
        val gambar = etGambar.text.toString().trim()

        if (judul.isEmpty() || genre.isEmpty() || sinopsis.isEmpty()) {
            Toast.makeText(this, "Isi semua data!", Toast.LENGTH_SHORT).show()
            return
        }

        val book = Book(judul = judul, genre = genre, sinopsis = sinopsis, gambar = gambar)
        firestore.collection("buku")
            .add(book)
            .addOnSuccessListener {
                Toast.makeText(this, "Berhasil disimpan!", Toast.LENGTH_SHORT).show()
                setResult(RESULT_OK)
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Gagal menyimpan: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
