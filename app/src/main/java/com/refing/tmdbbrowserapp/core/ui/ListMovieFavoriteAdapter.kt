package com.refing.tmdbbrowserapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.refing.tmdbbrowserapp.R
import com.refing.tmdbbrowserapp.core.domain.model.Movie
import com.refing.tmdbbrowserapp.databinding.ItemRowMovieBinding
import com.refing.tmdbbrowserapp.databinding.ItemRowMovieFavoriteBinding
import java.util.ArrayList

class ListMovieFavoriteAdapter : RecyclerView.Adapter<ListMovieFavoriteAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_movie_favorite, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowMovieFavoriteBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/original${data.photo}")
                    .into(imgItemPhoto)
                tvItemName.text = data.name
                tvItemDescription.text = data.description

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}