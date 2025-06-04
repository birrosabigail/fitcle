package com.birros.eksportfigmafitcle

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore

class EditBookActivity : AppCompatActivity() {
    private lateinit var etJudul: EditText
    private lateinit var etGenre: EditText
    private lateinit var etSinopsis: EditText
    private lateinit var btnUbah: Button
    private lateinit var btnHapus: Button
    private lateinit var firestore: FirebaseFirestore
    private lateinit var bookId: String
    private lateinit var etGambar: EditText
    private lateinit var imgPreview: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)

        etJudul = findViewById(R.id.etJudul)
        etGenre = findViewById(R.id.etGenre)
        etSinopsis = findViewById(R.id.etSinopsis)
        btnUbah = findViewById(R.id.btnUbah)
        btnHapus = findViewById(R.id.btnHapus)
        etGambar = findViewById(R.id.etGambar)
        imgPreview = findViewById(R.id.imgPreview)
        firestore = FirebaseFirestore.getInstance()
        bookId = intent.getStringExtra("bookId") ?: ""

        ambilData()

        btnUbah.setOnClickListener {
            val update = mapOf(
                "title" to etJudul.text.toString(),
                "genre" to etGenre.text.toString(),
                "synopsis" to etSinopsis.text.toString(),
                "gambar" to etGambar.text.toString()
            )
            firestore.collection("buku").document(bookId).update(update).addOnSuccessListener {
                Toast.makeText(this, "Buku diubah", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        btnHapus.setOnClickListener {
            firestore.collection("buku").document(bookId).delete().addOnSuccessListener {
                Toast.makeText(this, "Buku dihapus", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        etGambar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val url = s.toString()
                if (url.isNotEmpty()) {
                    Glide.with(this@EditBookActivity)
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

    private fun ambilData() {
        firestore.collection("buku").document(bookId).get().addOnSuccessListener { doc ->
            etJudul.setText(doc.getString("title"))
            etGenre.setText(doc.getString("genre"))
            etSinopsis.setText(doc.getString("synopsis"))
            etGambar.setText(doc.getString(("gambar")))
        }
    }
}