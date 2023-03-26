package com.refing.tmdbbrowserapp.feature.home


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.refing.tmdbbrowserapp.R
import com.refing.tmdbbrowserapp.core.ui.ListMovieAdapter
import com.refing.tmdbbrowserapp.databinding.ActivityMainBinding
import com.refing.tmdbbrowserapp.feature.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(){

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovies.setHasFixedSize(true)
        binding.rvMovies2.setHasFixedSize(true)
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.nav_drawer, menu)

//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        val searchView = menu.findItem(R.id.search).actionView as SearchView
//
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
//        searchView.queryHint = resources.getString(R.string.search_hint)
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                if (query.isEmpty()) {
//                    return true
//                } else {
//                    binding.viewMain.root.visibility = View.GONE
//                    binding.viewSearch.root.visibility = View.VISIBLE
//                    binding.viewSearch.progressBar.visibility = View.VISIBLE
//                    homeViewModel.query=query
//                    showSearchResult()
//                }
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                return false
//            }
//        })

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                val uri = Uri.parse("tmdbbrowserapp://favorite")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
                true
            }
            else -> true
        }
    }
    private fun showRecyclerList() {
        val listPopularMovieAdapter = ListMovieAdapter()
        listPopularMovieAdapter.onItemClick = { popularselect ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, popularselect)
            startActivity(intent)
        }
        val listUpcomingMovieAdapter = ListMovieAdapter()
        listUpcomingMovieAdapter.onItemClick = { upcomingselect ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, upcomingselect)
            startActivity(intent)
        }

        homeViewModel.popularmovies.observe(this) { movie ->
            if (movie != null) {
                when (movie) {
                    is com.refing.tmdbbrowserapp.core.data.Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is com.refing.tmdbbrowserapp.core.data.Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        listPopularMovieAdapter.setData(movie.data)
                    }
                    is com.refing.tmdbbrowserapp.core.data.Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = movie.message ?: getString(R.string.error)
                    }
                }
            }
        }

        homeViewModel.upcomingmovies.observe(this) { up ->
            if (up != null) {
                when (up) {
                    is com.refing.tmdbbrowserapp.core.data.Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is com.refing.tmdbbrowserapp.core.data.Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        listUpcomingMovieAdapter.setData(up.data)
                    }
                    is com.refing.tmdbbrowserapp.core.data.Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = up.message ?: getString(R.string.error)
                    }
                }
            }
        }

        with(binding.rvMovies) {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
            setHasFixedSize(true)
            adapter = listPopularMovieAdapter
        }
        with(binding.rvMovies2) {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
            setHasFixedSize(true)
            adapter = listUpcomingMovieAdapter
        }

    }

//    private fun showSearchResult() {
//        val listSearchMovieAdapter = ListMovieSearchAdapter()
//        listSearchMovieAdapter.onItemClick = { searchselect ->
//            val intent = Intent(this, DetailActivity::class.java)
//            intent.putExtra(DetailActivity.EXTRA_DATA, searchselect)
//            startActivity(intent)
//        }
//        homeViewModel.searchmovies.observe(this) { sc ->
//            if (sc != null) {
//                when (sc) {
//                    is com.refing.tmdbbrowserapp.core.data.Resource.Loading -> binding.viewSearch.progressBar.visibility = View.VISIBLE
//                    is com.refing.tmdbbrowserapp.core.data.Resource.Success -> {
//
//                        binding.viewSearch.progressBar.visibility = View.GONE
//                        listSearchMovieAdapter.setData(sc.data)
//                    }
//                    is com.refing.tmdbbrowserapp.core.data.Resource.Error -> {
//                        binding.viewSearch.progressBar.visibility = View.GONE
//                        binding.viewError.root.visibility = View.VISIBLE
//                        binding.viewError.tvError.text = sc.message ?: getString(R.string.error)
//                    }
//                }
//            }
//        }
//        with(binding.viewSearch.rvMovies3) {
//            layoutManager = LinearLayoutManager(context)
//            setHasFixedSize(true)
//            adapter = listSearchMovieAdapter
//        }
//    }





}