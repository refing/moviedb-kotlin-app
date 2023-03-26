package com.refing.tmdbbrowserapp.favorite

import android.app.SearchManager
import android.content.Context

import androidx.appcompat.widget.SearchView
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.refing.tmdbbrowserapp.R
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
            binding.viewMain.progressBar.visibility = View.GONE
            listMovieAdapter.setData(movie)
        }

        with(binding.viewMain.rvFavorites) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = listMovieAdapter
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.nav_drawer, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty()) {
                    return true
                } else {
                    binding.viewMain.root.visibility = View.GONE
                    binding.viewSearch.root.visibility = View.VISIBLE
                    binding.viewSearch.progressBar.visibility = View.VISIBLE
                    favoriteViewModel.query=query
                    showSearchResult()
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return true
    }
    private fun showSearchResult() {
        val listSearchMovieAdapter = ListMovieSearchAdapter()
        listSearchMovieAdapter.onItemClick = { searchselect ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, searchselect)
            startActivity(intent)
        }
        favoriteViewModel.searchmovies.observe(this) { sc ->
            binding.viewSearch.progressBar.visibility = View.GONE
            listSearchMovieAdapter.setData(sc)
        }
        with(binding.viewSearch.rvMovies3) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = listSearchMovieAdapter
        }
    }
}