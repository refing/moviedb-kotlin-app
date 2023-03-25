package com.refing.tmdbbrowserapp.feature.home


import android.content.Intent
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
import com.refing.tmdbbrowserapp.feature.favorite.FavoriteActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

//import androidx.navigation.findNavController
//import androidx.navigation.ui.AppBarConfiguration
//import androidx.navigation.ui.setupWithNavController
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import androidx.navigation.ui.setupActionBarWithNavController

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
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
//            R.id.action_setting -> {
//                val intent = Intent(this@MainActivity, ThemeActivity::class.java)
//                startActivity(intent)
//                return true
//            }
            R.id.action_favorite -> {
                val intent = Intent(this@MainActivity, FavoriteActivity::class.java)
                startActivity(intent)
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
//            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = listPopularMovieAdapter
        }
        with(binding.rvMovies2) {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
            setHasFixedSize(true)
            adapter = listUpcomingMovieAdapter
        }

    }





}