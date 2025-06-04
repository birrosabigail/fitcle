package com.birros.eksportfigmafitcle.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.birros.eksportfigmafitcle.R
import com.birros.eksportfigmafitcle.model.Book
import com.bumptech.glide.Glide

class BookAdapter(
    private val bookList: List<Book>,
    private val onItemClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_buku, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        holder.tvJudul.text = book.judul
        holder.tvGenre.text = book.genre
        holder.tvSinopsis.text = book.sinopsis

        Glide.with(holder.itemView.context)
            .load(book.gambar) // ← SUDAH BENAR
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imgBuku)

        holder.itemView.setOnClickListener { onItemClick(book) }
    }

    override fun getItemCount(): Int = bookList.size

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvJudul: TextView = itemView.findViewById(R.id.tvJudulBuku)
        val tvGenre: TextView = itemView.findViewById(R.id.tvGenre)
        val tvSinopsis: TextView = itemView.findViewById(R.id.tvSinopsis)
        val imgBuku: android.widget.ImageView = itemView.findViewById(R.id.imgBuku) // Tambahkan ini
    }
}

