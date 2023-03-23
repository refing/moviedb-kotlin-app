package com.refing.tmdbbrowserapp.feature.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.refing.tmdbbrowserapp.R
import com.refing.tmdbbrowserapp.core.domain.model.Movie
//import com.example.githubuserapp.core.ui.SectionsPagerAdapter
import com.refing.tmdbbrowserapp.databinding.ActivityDetailBinding
import com.refing.tmdbbrowserapp.core.ui.ViewModelFactory
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val movie = intent.getParcelableExtra<Movie>(EXTRA_DATA) as Movie

        binding.tvTitle.text = movie.name.toString()
        binding.tvDescription.text = movie.description.toString()
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/original${movie.photo}")
            .circleCrop()
            .into(binding.imgPhoto)
        binding.tvRatings.text = resources.getString(R.string.ratings, DecimalFormat("#.#").format(movie.vote_average), movie.vote_count.toString())
        val statusFavorite = movie.isFavorite
        binding.fabFavorite?.setOnClickListener {
            if (statusFavorite) {
                binding.fabFavorite?.setImageResource(R.drawable.ic_favorite)
                Toast.makeText(this, "Film berhasil ditambahkan ke daftar favorite", Toast.LENGTH_SHORT).show()
                detailViewModel.setFavoriteMovie(movie,true)
            } else {
                binding.fabFavorite?.setImageResource(R.drawable.ic_unfavorite)
                Toast.makeText(this, "Film berhasil dihapus dari daftar favorite", Toast.LENGTH_SHORT).show()
                detailViewModel.setFavoriteMovie(movie,true)
            }
        }
        supportActionBar?.elevation = 0f
    }

}