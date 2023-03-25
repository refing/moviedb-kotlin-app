package com.refing.tmdbbrowserapp.feature.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.refing.tmdbbrowserapp.databinding.ActivityFavoriteBinding
import com.refing.tmdbbrowserapp.databinding.ActivityMainBinding
import com.refing.tmdbbrowserapp.feature.detail.DetailActivity
import com.refing.tmdbbrowserapp.feature.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listMovieAdapter = ListMovieFavoriteAdapter()
        listMovieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteMovies.observe(this) { movie ->
            binding.progressBar.visibility = View.GONE
            listMovieAdapter.setData(movie)
        }

        with(binding.rvFavorites) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = listMovieAdapter
        }
    }
}