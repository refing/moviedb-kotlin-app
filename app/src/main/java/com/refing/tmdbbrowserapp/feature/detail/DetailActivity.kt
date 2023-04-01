package com.refing.tmdbbrowserapp.feature.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.refing.tmdbbrowserapp.R
import com.refing.tmdbbrowserapp.core.domain.model.Movie
import com.refing.tmdbbrowserapp.databinding.ActivityDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }
    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding
    private var buttonState: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        val movie = intent.getParcelableExtra<Movie>(EXTRA_DATA) as Movie

        supportActionBar?.title = movie.name
        binding.tvTitle.text = movie.name.toString()
        binding.tvDescription.text = movie.description.toString()
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/original${movie.photo}")
            .into(binding.imgPhoto)
        binding.tvRatings.text = resources.getString(R.string.ratings, DecimalFormat("#.#").format(movie.vote_average).toString())
        binding.tvRatings2.text = resources.getString(R.string.ratings2, movie.vote_count.toString())

        detailViewModel.favoriteMovies.observe(this) { favoriteList ->
            if (favoriteList != null) {
                for (data in favoriteList) {
                    if (movie.id == data.id) {
                        buttonState = true
                        binding.fabFavorite.setImageResource(R.drawable.ic_favorite)
                    }
                }
            }
        }
        binding.fabFavorite.setOnClickListener {
            if (!buttonState) {
                buttonState = true
                binding.fabFavorite.setImageResource(R.drawable.ic_favorite)
                Toast.makeText(this, "User berhasil ditambahkan ke daftar favorite", Toast.LENGTH_SHORT).show()
                detailViewModel.setFavoriteMovie(movie, true)
            } else {
                buttonState = false
                binding.fabFavorite.setImageResource(R.drawable.ic_unfavorite)
                Toast.makeText(this, "User berhasil dihapus dari daftar favorite", Toast.LENGTH_SHORT).show()
                detailViewModel.setFavoriteMovie(movie, false)
            }
        }

    }

}