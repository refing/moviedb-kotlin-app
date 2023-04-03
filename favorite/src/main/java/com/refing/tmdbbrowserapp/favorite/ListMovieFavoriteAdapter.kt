package com.refing.tmdbbrowserapp.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.refing.tmdbbrowserapp.core.domain.model.Movie
import com.refing.tmdbbrowserapp.favorite.databinding.ItemRowMovieFavoritesBinding
import java.util.ArrayList


@Suppress("DEPRECATION")
class ListMovieFavoriteAdapter(private val listMovie: ArrayList<Movie>) : RecyclerView.Adapter<ListMovieFavoriteAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowMovieFavoritesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listMovie[position]
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/original${data.photo}")
            .into(holder.binding.imgItemPhoto)
        holder.binding.tvItemName.text = data.name
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listMovie[holder.adapterPosition]) }

    }

    override fun getItemCount(): Int = listMovie.size

    class ListViewHolder(var binding: ItemRowMovieFavoritesBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Movie)
    }
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}