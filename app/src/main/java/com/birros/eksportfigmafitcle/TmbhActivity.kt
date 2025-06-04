package com.birros.eksportfigmafitcle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.birros.eksportfigmafitcle.adapter.BookAdapter
import com.birros.eksportfigmafitcle.model.Book
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class   TmbhActivity: AppCompatActivity() {
    private lateinit var rvBuku: RecyclerView
    private lateinit var btnAddBuku: Button
    private lateinit var bookAdapter: BookAdapter
    private val bookList = mutableListOf<Book>()
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContentView(R.layout.activity_tmbh)

        firestore = FirebaseFirestore.getInstance()
        rvBuku = findViewById(R.id.rvBuku)
        btnAddBuku = findViewById(R.id.btnAddBuku)

        bookAdapter = BookAdapter(bookList) { book ->
            val intent = Intent(this, EditBookActivity::class.java)
            intent.putExtra("bookId", book.id)
            startActivity(intent)
        }

        rvBuku.layoutManager = LinearLayoutManager(this)
        rvBuku.adapter = bookAdapter

        btnAddBuku.setOnClickListener {
            val intent = Intent(this, InputBukuActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_BOOK)
        }

        loadBooks()
    }

    private fun loadBooks() {
        firestore.collection("buku").get().addOnSuccessListener { documents ->
            bookList.clear()
            for (doc in documents) {
                val book = doc.toObject(Book::class.java)
                book.id = doc.id
                bookList.add(book)
            }
            bookAdapter.notifyDataSetChanged()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_BOOK && resultCode == RESULT_OK) {
            loadBooks()
        }
    }

    override fun onResume() {
        super.onResume()
        loadBooks()
    }

    companion object {
        const val REQUEST_CODE_ADD_BOOK = 1
    }
}
