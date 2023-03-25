package com.refing.tmdbbrowserapp.feature.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.refing.tmdbbrowserapp.R
import com.refing.tmdbbrowserapp.core.domain.model.Movie
//import com.example.githubuserapp.core.ui.SectionsPagerAdapter
import com.refing.tmdbbrowserapp.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat
@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }
    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val factory = ViewModelFactory.getInstance(this)
//        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val movie = intent.getParcelableExtra<Movie>(EXTRA_DATA) as Movie
        showDetailMovie(movie)

    }
    private fun showDetailMovie(movie: Movie?) {
        movie?.let {
            supportActionBar?.title = movie.name
//            binding.tvTitle.text = movie.name.toString()
            binding.tvDescription.text = movie.description.toString()
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/original${movie.photo}")
                .circleCrop()
                .into(binding.imgPhoto)
            binding.tvRatings.text = resources.getString(R.string.ratings, DecimalFormat("#.#").format(movie.vote_average), movie.vote_count.toString())

            var statusFavorite = movie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fabFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(movie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean){
        if (statusFavorite) {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
//            Toast.makeText(this, "Film berhasil ditambahkan dari daftar favorite", Toast.LENGTH_SHORT).show()
        } else {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_unfavorite))
//            Toast.makeText(this, "Film berhasil dihapus dari daftar favorite", Toast.LENGTH_SHORT).show()
        }
    }

}