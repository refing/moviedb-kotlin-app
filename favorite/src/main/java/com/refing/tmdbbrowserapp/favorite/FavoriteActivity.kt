package com.refing.tmdbbrowserapp.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.refing.tmdbbrowserapp.favorite.databinding.ActivityFavoriteBinding
import com.refing.tmdbbrowserapp.feature.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        val listMovieAdapter = ListMovieFavoriteAdapter()
        listMovieAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteMovies.observe(this) { movie ->
            binding.progressBar.visibility = View.GONE
            binding.viewEmpty.root.visibility = if (movie.isNotEmpty()) View.GONE else View.VISIBLE
            listMovieAdapter.setData(movie)
        }

        with(binding.rvFavorites) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = listMovieAdapter
        }
    }
}